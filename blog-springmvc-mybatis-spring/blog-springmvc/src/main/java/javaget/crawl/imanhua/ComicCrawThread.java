package javaget.crawl.imanhua;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javaget.crawl.ConnectionManager;
import javaget.crawl.imanhua.ComicCrawlerForImanhua.RestartCrawUncaughtExceptionHandler;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ComicCrawThread extends Thread {

	private Log log = LogFactory.getLog(getClass());

	private DefaultHttpClient client = ConnectionManager.getHttpClient();

	private String server = "http://www.imanhua.com";

	private String outputDir; // 保存漫画的的文件目录
	private String comicName; // 该漫画的中文名
	private List<String> list; // 存放该漫画每一集的链接
	private Map<String, String> map; // 每一集的链接做为key，每一集的名称作为value
	private Map<String, Integer> imgMap; // 每一集的链接做为key，每一集的图片总数作为value

	private final String url; // 该漫画的链接地址

	String prefix = "imanhua_";
	String suffix = ".png";

	public ComicCrawThread(String url) {
		this.url = url;
	}

	public String getHtmlByUrl(String url) {
		log.info("读取页面：" + url);
		HttpGet get = new HttpGet(url);
		HttpEntity entity = null;
		HttpResponse response = null;
		try {
			response = client.execute(get);
			entity = response.getEntity();
			if (entity != null) {
				InputStream is = entity.getContent();
				StringWriter sw = new StringWriter();
				IOUtils.copy(is, sw, "GB2312");
				is.close();
				sw.close();
				log.info("读取页面：" + url + "成功！");
				return sw.toString();
			}
		} catch (IOException e) {
			log.info("读取页面：" + url + "失败！");
		} finally {
			get.abort();
			EntityUtils.consumeQuietly(entity);
		}
		return null;
	}

	@Override
	public void run() {

		String html = getHtmlByUrl(url);
		Document doc = Jsoup.parse(html);
		Elements element = doc.select("#subBookList > li >a");
		int size = element.size();

		list = new ArrayList<String>(size);
		map = new HashMap<String, String>(size);
		imgMap = new HashMap<String, Integer>(size);

		Element h1 = doc.select(".bookInfo >h1").get(0);
		comicName = h1.text();

		URL resource = getClass().getClassLoader().getResource("");
		String location = resource.getFile().substring(1);
		int index = location.indexOf("WEB-INF/classes/");

		outputDir = location.substring(0, index) + "resources/imanhua";
		File f = new File(outputDir);
		if (!f.exists()) {
			f.mkdirs();
		}

		for (Iterator<Element> it = element.iterator(); it.hasNext();) {

			Element a = it.next();
			String href = server + a.attr("href");
			String title = a.attr("title");

			list.add(href);
			map.put(href, title);
		}

		WebClient webClient = ConnectionManager.getWebClient();
		for (int i = 0; i < list.size(); i++) {

			String href = list.get(i);
			String comicVolumn = map.get(href);

			File catg = new File(outputDir, comicName);
			if (!catg.exists()) {
				catg.mkdirs();
			}

			File saveDir = new File(catg, comicVolumn);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}

			File indexHtml = new File(saveDir, "index.html");
			if (indexHtml.exists()) {
				log.info(comicVolumn + "已经存在，将跳过");
				continue;
			}

			HtmlPage page = null;
			try {
				page = webClient.getPage(href);
			} catch (IOException e) {
				e.printStackTrace();
			}

			HtmlSelect select = (HtmlSelect) page
					.getByXPath(
							"//div[@class='w980 tc']//div[@class='main-btn']//select[@id='pageSelect']")
					.get(0);
			List<HtmlOption> options = select.getOptions();
			imgMap.put(href, options.size());
			log.info(comicVolumn + "共有" + options.size() + "张图片");

			for (int j = 1; j <= imgMap.get(href); j++) {

				File file = new File(saveDir, prefix
						+ (j / 10 == 0 ? "00" + j : "0" + j) + suffix);
				/*
				if (file.exists()) {
					log.info(comicVolumn + "目录下的图片" + file.getName()
							+ "已被保存，将跳过");
					continue;
				}
				*/
				String pageUrl = href + "?p=" + j;
				try {
					page = webClient.getPage(pageUrl);
					HtmlImage img = (HtmlImage) page.getByXPath(
							"//div[@id='mangaBox']//img[@id='mangaFile']").get(
							0);
					String src = img.getAttribute("src");
					log.info("读取到图片地址：" + src);

					HttpGet get = new HttpGet(src);
					get.setHeader("Referer", pageUrl);

					HttpResponse response = client.execute(get);
					HttpEntity entity = response.getEntity();
					log.info("正在保存图片>>>." + src);

					log.info("图片保存的文件路径是：" + file.getAbsolutePath());
					InputStream is = entity.getContent();
					OutputStream os = new BufferedOutputStream(
							new FileOutputStream(file));

					IOUtils.copy(is, os);
					IOUtils.closeQuietly(os);
					IOUtils.closeQuietly(is);

				} catch (IOException e) {
					e.printStackTrace();
					/**
					 * 出现未知IO错误，重启抓取线程
					 * 
					 * TODO:找出为何抓取过程中会偶尔出现死锁
					 
					ComicCrawThread thread = new ComicCrawThread(url);
					thread.setUncaughtExceptionHandler(new RestartCrawUncaughtExceptionHandler(
							url));
					thread.start();
					return;
					*/
				}
			}
			webClient.closeAllWindows();

			Map<String, Object> root = new HashMap<String, Object>();
			root.put("count", imgMap.get(href));
			root.put("comicName", comicName);
			root.put("comicVolumn", comicVolumn);

			String prev = "";
			String next = "";

			int p = i - 1;
			int n = i + 1;

			if (p != -1) {
				prev = map.get(list.get(p));
			}
			if (n != size) {
				next = map.get(list.get(n));
			}
			root.put("prev", next);
			root.put("next", prev);

			Configuration cfg = new Configuration();

			try {
				// cfg.setDirectoryForTemplateLoading(new
				// File(templateDir));

				URL res = Thread.currentThread().getContextClassLoader()
						.getResource("crawl");

				cfg.setDirectoryForTemplateLoading(new File(res.getFile()
						.substring(1)));
				cfg.setObjectWrapper(new DefaultObjectWrapper());
				cfg.setDefaultEncoding("UTF-8");

				Template temp;
				try {
					temp = cfg.getTemplate("index.ftl");
					temp.setEncoding("UTF-8");

					File output = new File(saveDir, "index.html");
					if (output.exists()) {
						output.delete();
					}

					Writer out = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(output), "UTF-8"));

					temp.process(root, out);
					out.flush();
					out.close();

				} catch (IOException e) {
					e.printStackTrace();
				} catch (TemplateException e) {
					e.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
