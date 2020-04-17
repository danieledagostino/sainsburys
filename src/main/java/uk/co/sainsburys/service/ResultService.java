package uk.co.sainsburys.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import uk.co.sainsburys.domain.Product;
import uk.co.sainsburys.domain.ProductList;

public class ResultService {
	private final Logger logger = Logger.getLogger(ResultService.class);

	private static final String PRODUCT_CLASS = "productLister";
	
	public ResultService() {
	}

	public ProductList getResults(Document document) throws IOException {
		SearchService service = new SearchService();
		ProductList results = new ProductList();
		Element element = document.getElementById(PRODUCT_CLASS);

		Elements elements = element.getElementsByTag("li").attr("class", "gridItem");
		elements = element.getElementsByTag("a");
		
		Set<String> urls = new HashSet<String>();

		for (int i = 1; i < elements.size(); i++) {
			urls.add(service.getProductUrl(elements.get(i)));
		}
		
		Product product = null;
		for (String url : urls) {
			try {
				product = service.getProduct(url);
				results.addResult(product);
			} catch (Exception ex) {
				logger.error("Product not added");
				logger.error(url);
			}
		}

		return results;
	}

}
