package other.sax;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;

public class ParseTest {
	@Test
	public void testSAX() throws Throwable {
		SaxParseService sax = new SaxParseService();
		InputStream input = this.getClass().getClassLoader()
				.getResourceAsStream("other/sax/book.xml");
		List<Book> books = sax.getBooks(input);
		for (Book book : books) {
			System.out.println(book.toString());
		}
	}
}
