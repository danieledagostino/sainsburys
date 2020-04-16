package uk.co.sainsburys.service;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import uk.co.sainsburys.domain.ProductList;

public class ResultService {

	private static final String PRODUCT_CLASS = "productNameAndPromotions";
	
	public ResultService() {
	}

	public ProductList getResults(Document document) throws IOException {
		SearchService service = new SearchService();
		ProductList results = new ProductList();
		Elements elements = document.getElementsByTag("div").attr("class", "productNameAndPromotions");

		for (Element element : elements) {
			results.addResult(service.getProduct(element));
		}

		return results;
	}

}
