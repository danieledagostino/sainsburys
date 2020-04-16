package uk.co.sainsburys.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import uk.co.sainsburys.domain.Product;

public class SearchService {
	private static final Logger logger = Logger.getLogger(SearchService.class);
	
	public SearchService() {
	}

	public Product getProduct(Element element) throws IOException {

		Product result = new Product();

		result.setTitle(getTitle(element));
		result.setUnitPrice(getUnitPrice(element));

		String url = getProductUrl(element);
		if (url != null) {
			Document productPage = Jsoup.connect(url).get();
			result.setDescription(getDescription(productPage));
			result.setKcal(getKcal(productPage));
		}

		return result;
	}

	private String getProductUrl(Element element) {
		try {
			Element a = element.getElementsByTag("a").first();
			return a.attr("href");
		} catch (Exception e) {
			logger.error("Failed to find url", e);
			return null;
		}
	}

	private String getTitle(Element element) {
		try {
			Element a = element.getElementsByClass("productInfo").first().getElementsByTag("a").first();
			List<TextNode> textNodes = a.textNodes();
			return textNodes.get(0).text();
		} catch (Exception e) {
			logger.error("Failed to find title", e);
			return null;
		}
	}

	private BigDecimal getUnitPrice(Element element) {
		try {
			Element a = element.getElementsByClass("pricePerUnit").first();
			List<TextNode> textNodes = a.textNodes();
			String text = textNodes.get(0).text();
			String number = text.replaceAll("[&pound, ]", "");
			BigDecimal price = new BigDecimal(number);
			return price;
		} catch (Exception e) {
			logger.error("Failed to find Unit Price", e);
			return null;
		}
	}

	private String getDescription(Document document) {
		Elements elements = document.getElementsByTag("meta").attr("name", "description");
		String description = "";
		for (Element element : elements) {
			Element attr = element.attr("name", "description");
			if (attr != null) {
				description = element.attr("content");
				break;
			}
		}
		return description;
	}
	
	private String getKcal(Document document) {
		try {
			Element element = document.getElementsByTag("table").attr("class", "nutritionTable").first();
			Element kcalEl = element.getElementsByTag("td").attr("class", "tableRow0").first();
					
			List<TextNode> textNodes = kcalEl.textNodes();
			String text = textNodes.get(0).text();
			return text;
		} catch (Exception e) {
			logger.error("Failed to find Calories per 100g", e);
			return null;
		}
	}

}
