package uk.co.sainsburys;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.sainsburys.domain.ProductList;
import uk.co.sainsburys.service.ResultService;

public class MainProducer {
	
	private static final String HIRING_TESTS_S3_WEBSITE = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
	private static final Logger logger = Logger.getLogger(MainProducer.class);
	
	public static void main(String[] args) {

		Document document;
		try {
			System.setProperty("https.protocols", "TLSv1.2");
			
			document = Jsoup.connect(HIRING_TESTS_S3_WEBSITE).get();

			ProductList results = new ResultService().getResults(document);

			ObjectMapper om = new ObjectMapper();
			
			logger.info(om.writerWithDefaultPrettyPrinter().writeValueAsString(results));
		} catch (IOException e) {
			logger.error(e);
		}

	}
}
