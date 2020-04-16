package uk.co.sainsburys.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import uk.co.sainsburys.domain.ProductList;
import uk.co.sainsburys.service.ResultService;

public class SainsburysTestSuite {

	private static final String HIRING_TESTS_S3_WEBSITE = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

	@Test
	public void A_assertResults() throws IOException {

		Document document = Jsoup.connect(HIRING_TESTS_S3_WEBSITE).get();

		ProductList results = new ResultService().getResults(document);

//		assertEquals(7, results.size());
//		assertEquals(15.10, results.getTotal().doubleValue(), 0);

	}
}
