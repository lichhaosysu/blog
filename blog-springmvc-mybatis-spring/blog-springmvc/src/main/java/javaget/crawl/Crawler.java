package javaget.crawl;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

public class Crawler {
	
	private Log log = LogFactory.getLog(getClass());

	private DefaultHttpClient client = ConnectionManager.getHttpClient();

	private String outputDir = "output";

	private String server = "http://www.imanhua.com";
	private String url = "http://www.imanhua.com/comic/54/";// 火影忍者
//	private String url = "http://www.imanhua.com/comic/55/";// 海贼王
//	private String url = "http://www.imanhua.com/comic/120/";//死神

	private int CRAWL_SIZE = 20; //希望抓取该漫画的集数

	private String comicName; //该漫画的中文名
	private List<String> list;  //存放该漫画每一集的链接
	private Map<String, String> map; //每一集的链接做为key，每一集的名称作为value
	private Map<String, Integer> imgMap; //每一集的链接做为key，每一集的图片总数作为value


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
		} catch (Exception e) {
			log.info("读取页面：" + url + "失败！");
			return null;
		} finally {
			get.abort();
			EntityUtils.consumeQuietly(entity);
		}
		return null;
	}

	public Crawler collectUrls() {

		String html = getHtmlByUrl(url);
		Document doc = Jsoup.parse(html);
		Elements element = doc.select("#subBookList > li >a");
		int size = element.size();

		list = new ArrayList<String>(size);
		map = new HashMap<String, String>(size);
		imgMap = new HashMap<String, Integer>(size);

		for (Iterator<Element> it = element.iterator(); it.hasNext();) {
			Element a = it.next();
			String href = a.attr("href");
			String title = a.attr("title");

			list.add(server + href);
			map.put(server + href, title);

		}

		Element h1 = doc.select(".bookInfo >h1").get(0);
		comicName = h1.text();

		return this;

	}

	public Crawler collectImgSizes() throws Exception {

		WebClient webClient = ConnectionManager.getWebClient();

		for (int i = 0; i < CRAWL_SIZE; i++) {
			String url = list.get(i);
			HtmlPage page = webClient.getPage(url);

			HtmlSelect select = (HtmlSelect) page
					.getByXPath(
							"//div[@class='w980 tc']//div[@class='main-btn']//select[@id='pageSelect']")
					.get(0);
			List<HtmlOption> options = select.getOptions();
			imgMap.put(url, options.size());
			log.info(map.get(url) + "共有" + options.size() + "张图片");
		}

		webClient.closeAllWindows();
		return this;
	}

	public void collectImages() {

		URL resource = Crawler.class.getClassLoader().getResource("");
		
		String location = resource.getFile().substring(1);
		int index = location.indexOf("WEB-INF/classes/");
		if(index != -1){
			outputDir = location.substring(0, index)+"resources";	
		}
		
		File catg = new File(outputDir, comicName);
		/*
		if (catg.exists()) {
			File[] volumns = catg.listFiles();
			for (int i = 0; i < volumns.length; i++) {
				File file = volumns[i];
				if (file.isFile()) {
					file.delete();
				}
				if (file.isDirectory()) {
					File[] subFiles = file.listFiles();
					for (File f : subFiles) {
						if (f.isFile()) {
							f.delete();
						}
					}
					file.delete();
				}
			}
			catg.delete();
		}
		catg.mkdirs();
		*/
		if(!catg.exists()){
			catg.mkdirs();
		}
		
		ExecutorService exec = Executors.newFixedThreadPool(CRAWL_SIZE);
		for (int i = 0; i < CRAWL_SIZE; i++) {
			CrawlImgThread thread = new CrawlImgThread(i);
			if (!exec.isShutdown()) {
				exec.execute(thread);
			}
		}
		exec.shutdown();
	}

	private class CrawlImgThread extends Thread {

		private int index;

		public CrawlImgThread() {
		}

		public CrawlImgThread(int index) {
			this.index = index;
		}

		@Override
		public void run() {

			String url = list.get(index);
			String dir = map.get(url);
			int size = imgMap.get(url);

			File catg = new File(outputDir, comicName);
			File saveDir = new File(catg, dir);
			if(!saveDir.exists()){
				saveDir.mkdirs();	
			}

			WebClient webClient = ConnectionManager.getWebClient();

			String prefix = "imanhua_";
			String suffix = ".png";
			for (int i = 1; i <= size; i++) {
				String pageUrl = url + "?p=" + i;
				try {
					HtmlPage page = webClient.getPage(pageUrl);
					HtmlImage img = (HtmlImage) page.getByXPath(
							"//div[@id='mangaBox']//img[@id='mangaFile']").get(
							0);
					String src = img.getAttribute("src");
					log.info("第" + (index + 1) + "个线程：读取到图片地址：" + src);

					HttpGet get = new HttpGet(src);
					get.setHeader("Referer", pageUrl);

					HttpResponse response = client.execute(get);
					HttpEntity entity = response.getEntity();
					log.info("正在保存图片>>>." + src);
					InputStream is = entity.getContent();
					File file = new File(saveDir,
							prefix + (i / 10 == 0 ? "00" + i : "0" + i)
									+ suffix);
					log.info("图片保存的文件路径是："+file.getAbsolutePath());
					
					OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
					
					IOUtils.copy(is, os);
					IOUtils.closeQuietly(os);
					IOUtils.closeQuietly(is);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			webClient.closeAllWindows();

			Map<String, Object> root = new HashMap<String, Object>();
			root.put("count", size);
			root.put("comicName", comicName);
			root.put("comicVolumn", dir);

			String prev = "";
			String next = "";

			int p = index - 1;
			int n = index + 1;

			if (p != -1) {
				prev = map.get(list.get(p));
			}
			if (n != CRAWL_SIZE) {
				next = map.get(list.get(n));
			}
			root.put("prev", next);
			root.put("next", prev);

			Configuration cfg = new Configuration();

			try {
				// cfg.setDirectoryForTemplateLoading(new File(templateDir));

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

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Crawler crawler = new Crawler();
		crawler.collectUrls().collectImgSizes().collectImages();
	}
	
	public void crawl() throws Exception{
		Crawler crawler = new Crawler();
		crawler.collectUrls().collectImgSizes().collectImages();
	}

}
