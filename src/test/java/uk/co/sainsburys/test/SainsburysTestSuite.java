package uk.co.sainsburys.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Suite.SuiteClasses;

import uk.co.sainsburys.domain.ProductList;
import uk.co.sainsburys.service.ResultService;

public class SainsburysTestSuite {

	private static final String HIRING_TESTS_S3_WEBSITE = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

	@Test
	public void A_assertResults() throws IOException {

		System.setProperty("https.protocols", "TLSv1.2");
		
		Document document = Jsoup.connect(HIRING_TESTS_S3_WEBSITE).get();

		ProductList results = new ResultService().getResults(document);

		Assert.assertEquals(18, results.getProducts().size());
		Assert.assertEquals(42.00, results.getTotal().getGross());

	}
}
