package org.lambdaj.poc;

public enum BrandType {
	VW(1,"Volks Wagen"),
	FT(1,"Fiat"),
	AD(1,"Audi");
	
	private Integer brandCode;
	private String  brandString;
	
	private BrandType(Integer brandCode, String brandString) {
		this.brandCode = brandCode;
		this.brandString = brandString;
	}

	public Integer getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(Integer brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandString() {
		return brandString;
	}

	public void setBrandString(String brandString) {
		this.brandString = brandString;
	}

	@Override
	public String toString() {
		return this.brandString;
	}
	
}
