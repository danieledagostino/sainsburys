package uk.co.sainsburys.service;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import uk.co.sainsburys.domain.Results;

public class ResultService {

	private static final String PRODUCT_CLASS = "product";
	
	public ResultService() {
	}

	public Results getResults(Document document) throws IOException {
		SearchService service = new SearchService();
		Results results = new Results();
		Elements elements = document.getElementsByClass(PRODUCT_CLASS);

		for (Element element : elements) {
			results.addResult(service.getProduct(element));
		}

		return results;
	}

}
