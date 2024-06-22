package ims.enums;


/**
 * Enum of product categories.
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
public enum CategoryEnum {
	/*
	 * 0: tulips
	 */
	TULIPS("0", "tulips"),
	/**
	 * 1: crocus.
	 */
	CROCUS("1", "crocus"),
	/**
	 * 2: hyacinth.
	 */
	HYACINTH("2", "hyacinth"),
	/**
	 * 3: others.
	 */
	OTHERS("3", "others");

	/** category code */
	private String categoryCode;

	/** category name */
	private String category;

	/**
	 * generate category
	 *
	 * @param categoryCode  category code
	 * @param category  category
	 */
	private CategoryEnum(String categoryCode, String category) {
		this.categoryCode = categoryCode;
		this.category = category;
	}

	/**
	 * get category code
	 *
	 * @return category code
	 */
	public String getCategoryCode() {
		return this.categoryCode;
	}

	/**
	 * get category name
	 *
	 * @return category name
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * get category by category code
	 *
	 * @param category code
	 * @return category
	 */
	public static CategoryEnum getValueByCode(String categoryCode) {
		for (CategoryEnum category : CategoryEnum.values()) {
			if (category.getCategoryCode().equals(categoryCode)) {
				return category;
			}
		}
		return null;
	}
}