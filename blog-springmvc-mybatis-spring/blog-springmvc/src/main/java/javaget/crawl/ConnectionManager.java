package javaget.crawl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class ConnectionManager {

	private static int TIMEOUT = 20000;// 连接超时时间
	private static int SO_TIMEOUT = 20000;// 数据传输超时
	private static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1"
			+ " (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1";

	private static boolean useProxy;
	private static String proxyHostName;
	private static int proxyPort;

	private static PoolingClientConnectionManager cm;
	private static HttpParams params;

	static {
		// 初始化代理设置
		InputStream resourceAsStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("crawl/proxy.properties");
		Properties prop = new Properties();
		try {
			prop.load(resourceAsStream);
			useProxy = Boolean.valueOf(prop.getProperty("useProxy", "false"));
			proxyHostName = prop.getProperty("proxyHostName", "");
			proxyPort = Integer.parseInt(prop.getProperty("proxyPort", "0"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 初始化HttpClient配置
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));
		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory
				.getSocketFactory()));

		cm = new PoolingClientConnectionManager(schemeRegistry);
		cm.setMaxTotal(500);
		cm.setDefaultMaxPerRoute(200);

		params = new BasicHttpParams();
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIMEOUT);
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
		params.setParameter(CoreProtocolPNames.USER_AGENT, USER_AGENT);
		if (useProxy) {
			params.setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost(
					proxyHostName, proxyPort));// 设置代理
		}
	}

	public static synchronized DefaultHttpClient getHttpClient() {
		DefaultHttpClient client = new DefaultHttpClient(cm, params);
		return client;
	}

	public static synchronized WebClient getWebClient() {
		WebClient webClient = null;
		if (useProxy) {
			webClient = new WebClient(BrowserVersion.FIREFOX_10, proxyHostName,
					proxyPort);
		} else {
			webClient = new WebClient(BrowserVersion.FIREFOX_10);
		}
		return webClient;
	}
	
}
