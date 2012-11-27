package other;

import java.util.Properties;

public class GetOSName {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties prop = System.getProperties();
		String os = (String)prop.getProperty("os.name");
		System.out.println(os);
	}

}
