package uk.co.sainsburys.test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import uk.co.sainsburys.domain.Product;
import uk.co.sainsburys.domain.ProductList;
import uk.co.sainsburys.service.ResultService;

public class SainsburysTestSuite {

	private static final String HIRING_TESTS_S3_WEBSITE = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

	private Document mockingDocument;
	private final String fileName = "mock_webpage_with_3_product.html";
	
	@Before
	public void getDocument(String url) throws IOException {
		System.setProperty("https.protocols", "TLSv1.2");
		
		InputStream inputFile = this.getClass().getResourceAsStream(fileName);
		mockingDocument = Jsoup.parse(inputFile, "UTF-8", "");
	}
	
	@Test
	@Ignore
	public void A_assertRealCountProduct() throws IOException {

		Document document = Jsoup.connect(HIRING_TESTS_S3_WEBSITE).get();

		ProductList results = new ResultService().getResults(document);

		Assert.assertEquals(18, results.getProducts().size());

	}
	
	@Test
	@Ignore
	public void B_assertRealPriceAddition() throws IOException {

		Document document = Jsoup.connect(HIRING_TESTS_S3_WEBSITE).get();

		ProductList results = new ResultService().getResults(document);
		
		BigDecimal tot = BigDecimal.ZERO; 

		for (Product product : results.getProducts()) {
			tot.add(product.getUnitPrice());
		}

		Assert.assertEquals(42.00, tot);

	}
	
	@Test
	public void C_assertMockResults() throws IOException {

		ProductList results = new ResultService().getResults(mockingDocument);

		Assert.assertEquals(3, results.getProducts().size());

	}
	
	@Test
	public void B_assertMockPriceAddition() throws IOException {

		ProductList results = new ResultService().getResults(mockingDocument);

		BigDecimal tot = BigDecimal.ZERO; 

		for (Product product : results.getProducts()) {
			tot.add(product.getUnitPrice());
		}

		Assert.assertEquals(42.00, tot);

	}
}
