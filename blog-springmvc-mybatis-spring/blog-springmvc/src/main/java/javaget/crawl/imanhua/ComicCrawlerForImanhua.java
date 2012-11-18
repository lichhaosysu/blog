package javaget.crawl.imanhua;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * 用来启动抓取漫画线程的主类，配置在Spring容器中
 * 
 * <p>
 * 启动的线程数及抓取的漫画地址由comicsToCraw进行配置，在InitializingBean接口的
 * afterPropertiesSet方法中根据配置启动抓取线程
 * 
 * @author Stefan
 * @since 2012-11-17
 */

public class ComicCrawlerForImanhua implements InitializingBean {

	private Log log = LogFactory.getLog(getClass());

	/**
	 * 需要进行抓取的漫画在爱漫画网站http://www.imanhua.com/上的链接地址
	 * 
	 * <p>
	 * 比如火影忍者：http://www.imanhua.com/comic/54/
	 */
	private String[] comicsToCraw = new String[0];

	public String[] getComicsToCraw() {
		return comicsToCraw;
	}

	public void setComicsToCraw(String[] comicsToCraw) {
		this.comicsToCraw = comicsToCraw;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		int length = comicsToCraw.length;
		if (length > 0) {
			log.info("正在启动爱漫画网站抓取线程：" + Arrays.toString(comicsToCraw));
			for (int i = 0; i < length; i++) {
				String url = comicsToCraw[i];
				ComicCrawThread thread = new ComicCrawThread(url);
				thread.setUncaughtExceptionHandler(new RestartCrawUncaughtExceptionHandler(url));
				thread.start();
			}
			log.info("启动爱漫画网站抓取线程成功！" + Arrays.toString(comicsToCraw));
		} else {
			log.warn("没有指定爱漫画网站任何链接地址，爱漫画网站抓取将跳过！");
		}

	}
	
	public static class RestartCrawUncaughtExceptionHandler implements UncaughtExceptionHandler{
		
		private Log log = LogFactory.getLog(getClass());
		
		private String url;
		
		public RestartCrawUncaughtExceptionHandler(String url){
			this.url = url;
		}
		
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			log.error("地址为："+url+"的抓取线程发生了uncaught Exception!");
			/**
			 * 重启出现异常的抓取线程
			 */
			ComicCrawThread thread = new ComicCrawThread(url);
			thread.setUncaughtExceptionHandler(new RestartCrawUncaughtExceptionHandler(url));
			thread.start();
		}
	}
}
