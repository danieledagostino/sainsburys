package uk.co.sainsburys.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Results extends ArrayList<Product> {


	private static final long serialVersionUID = -4797006624972921359L;
	
	private BigDecimal total = new BigDecimal(0).setScale(2, RoundingMode.HALF_DOWN);

	public void addResult(Product result) {
		this.add(result);
		total = total.add(result.getUnitPrice());
	}

	public BigDecimal getTotal() {
		return total;
	}

}
