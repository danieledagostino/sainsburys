package uk.co.sainsburys.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import uk.co.sainsburys.domain.Product;
import uk.co.sainsburys.domain.ProductList;
import uk.co.sainsburys.service.ResultService;

@RunWith(JUnit4.class)
public class SainsburysTestSuite {

	private static final String REAL_WEBSITE = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
	private static final String MOCK_WEBSITE = "https://raw.githubusercontent.com/danieledagostino/sainsburys/master/src/test/resources/mock_webpage_with_3_product.html";

	private static Document realDocument;
	private static Document mockingDocument;

	@BeforeClass
	public static void init() {
		System.setProperty("https.protocols", "TLSv1.2");
		
		try {
			realDocument = Jsoup.connect(REAL_WEBSITE).get();
			mockingDocument = Jsoup.connect(MOCK_WEBSITE).get();
		} catch (IOException e) {
		}
	}

	@Test
	//@Ignore
	public void A_assertRealCountProduct() {

		ProductList results = null;
		try {

			results = new ResultService().getResults(realDocument);
			Assert.assertEquals(18, results.getProducts().size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	//@Ignore
	public void B_assertRealPriceAddition() {

		ProductList results = null;
		BigDecimal assertationResult = new BigDecimal(42.00).setScale(2, RoundingMode.HALF_DOWN);
		try {
			results = new ResultService().getResults(realDocument);
			BigDecimal tot = BigDecimal.ZERO;

			for (Product product : results.getProducts()) {
				tot = tot.add(product.getUnitPrice());
			}

			Assert.assertEquals(assertationResult, tot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void C_assertMockResults() {

		ProductList results = null;
		try {
			results = new ResultService().getResults(mockingDocument);
			Assert.assertEquals(4, results.getProducts().size());
		} catch (IOException e) {
		}

	}

	@Test
	public void D_assertMockPriceAddition() {

		ProductList results = null;
		BigDecimal assertationResult = new BigDecimal(9.25).setScale(2, RoundingMode.HALF_DOWN);
		try {
			results = new ResultService().getResults(mockingDocument);
			BigDecimal tot = BigDecimal.ZERO;

			for (Product product : results.getProducts()) {
				tot = tot.add(product.getUnitPrice());
			}

			Assert.assertEquals(assertationResult, tot);
		} catch (IOException e) {
		}

	}
}
