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
	private final Logger logger = Logger.getLogger(SearchService.class);

	private final String COMMON_DOMAIN_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/";
	private final String COMMON_STR_TO_REPLEACE = "../../../../../../";

	public SearchService() {
	}

	public Product getProduct(String url) throws IOException {

		Product product = new Product();
		Document productPage = Jsoup.connect(url).get();

		product.setTitle(getTitle(productPage));
		product.setUnitPrice(getUnitPrice(productPage));
		product.setDescription(getDescription(productPage));
		product.setKcal(getKcal(productPage));

		return product;
	}

	public String getProductUrl(Element element) {
		try {
			Element a = element.getElementsByTag("a").first();
			String url = a.attr("href");
			return url.replace(COMMON_STR_TO_REPLEACE, COMMON_DOMAIN_URL);
		} catch (Exception e) {
			logger.error("Failed to find url", e);
			return null;
		}
	}

	private String getTitle(Element element) {
		Element h1Tag = null;
		try {
			h1Tag = element.getElementsByClass("productTitleDescriptionContainer").first().getElementsByTag("h1")
					.first();
			List<TextNode> textNodes = h1Tag.textNodes();
			return textNodes.get(0).text();
		} catch (Exception e) {
			logger.error("Failed to find title");
			throw e;
		}
	}

	private BigDecimal getUnitPrice(Element element) {
		try {
			Element a = element.getElementsByClass("pricePerUnit").first();
			List<TextNode> textNodes = a.textNodes();
			String text = textNodes.get(0).text();
			String number = text.trim().replaceAll("[&£, ]", "");
			BigDecimal price = new BigDecimal(number);
			return price;
		} catch (Exception e) {
			logger.error("Failed to find Unit Price");
			throw e;
		}
	}

	private String getDescription(Element element) {
		Elements elements = null;
		try {
			elements = element.getElementsByTag("meta").attr("name", "description");
			String description = elements.get(1).attr("content");
			return description;
		} catch (Exception e) {
			logger.error("Failed to find Unit Price");
			throw e;
		}
	}

	private String getKcal(Element element) {
		Element nutritionTable = null;
		Element kcalElement = null;
		try {
			nutritionTable = element.getElementsByTag("table").attr("class", "nutritionTable").first();
			kcalElement = element.getElementsByTag("td").attr("class", "tableRow0").first();

			List<TextNode> textNodes = kcalElement.textNodes();
			String text = textNodes.get(0).text();
			return text;
		} catch (Exception e) {
			logger.error("Failed to find Calories per 100g");
			return "";
		}
	}

}
