package uk.co.sainsburys.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
	private String title;
	private String size;
	private BigDecimal unitPrice;
	private String description;

	/**
	 * @return The title of the item
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            The title of the item.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return The size (in kb) of the linked HTML.
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size
	 *            The size (in kb) of the linked HTML.
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return The price of one unit of the item.
	 */
	@JsonProperty("unit_price")
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            The price of one unit of the item.
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return The description of the item.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            The description of the item.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
