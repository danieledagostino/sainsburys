package uk.co.sainsburys.gateway;

import java.io.IOException;

import org.jsoup.nodes.Document;

public interface PageReader {

	/**
	 * Get the web page indicated by the url as a jsoup {@link Document}
	 * 
	 * @param url
	 * @return a jsoup {@link Document} representation of the web page
	 * @throws IOException
	 */
	Document getAsJsoupDocument(String url) throws IOException;

	/**
	 * Gets the size in kb of the web page indicated by the url (excluding page
	 * assets)
	 * 
	 * @param url
	 * @return The size of the web page (excluding page assets)
	 * @throws IOException
	 */
	String getWebpageSizeInKb(String url) throws IOException;

}