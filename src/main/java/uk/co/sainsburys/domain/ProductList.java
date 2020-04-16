package uk.co.sainsburys.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductList {

	List<Product> products = new ArrayList<Product>();
	
	private Total total = new Total();

	public void addResult(Product result) {
		this.products.add(result);
		total.addGross(result.getUnitPrice());
	}

	public Total getTotal() {
		return total;
	}

}
