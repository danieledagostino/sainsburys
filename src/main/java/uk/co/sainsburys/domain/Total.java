package uk.co.sainsburys.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Total {

	private BigDecimal gross = new BigDecimal(0).setScale(2, RoundingMode.HALF_DOWN);
	private BigDecimal vat = new BigDecimal(0).setScale(2, RoundingMode.HALF_DOWN);
	
	private final BigDecimal twenty = new BigDecimal(20);
	private final BigDecimal hundred = new BigDecimal(100);
	
	public BigDecimal getGross() {
		return gross;
	}
	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}
	public BigDecimal getVat() {
		return vat;
	}
	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}
	
	public void addGross(BigDecimal unitPrice) {
		this.gross = this.gross.add(unitPrice);
		this.vat = this.gross.multiply(twenty).divide(hundred);
	}
	
}
