package uk.co.sainsburys.gateway;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebPageReader implements PageReader {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uk.co.usryders.sainsburys.techtest.gateway.PageReader#getAsJsoupDocument(
	 * java.lang.String)
	 */
	@Override
	public Document getAsJsoupDocument(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		return doc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uk.co.usryders.sainsburys.techtest.gateway.PageReader#getWebpageSizeInKb(
	 * java.lang.String)
	 */
	@Override
	public String getWebpageSizeInKb(String url) throws IOException {
		// Connect to the page indicated and get the content length
		URL myURL = new URL(url);
		URLConnection myURLConnection = myURL.openConnection();

		myURLConnection.connect();
		int length = myURLConnection.getContentLength();

		myURLConnection.getInputStream().close();

		// Convert the length to kilobytes with one decimal place
		BigDecimal size = new BigDecimal(length).setScale(1, RoundingMode.HALF_DOWN);
		BigDecimal sizekb = size.divide(new BigDecimal(1024), RoundingMode.HALF_DOWN).setScale(1,
				RoundingMode.HALF_DOWN);

		// Convert to a string with units
		return sizekb + "kb";
	}

}
