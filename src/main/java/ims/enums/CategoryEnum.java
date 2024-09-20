package ims.enums;


/**
 * カテゴリーのEnum
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
public enum CategoryEnum {
	/*
	 * 0: チューリップ
	 */
	TULIPS("0", "チューリップ"),
	/**
	 * 1:  クロッカス
	 */
	CROCUS("1", "クロッカス"),
	/**
	 * 2: ヒヤシンス
	 */
	HYACINTH("2", "ヒヤシンス"),
	/**
	 * 3: その他
	 */
	OTHERS("3", "その他");

	/** カテゴリーコード */
	private String categoryCode;

	/** カテゴリー名 */
	private String category;

	/**
	 * カテゴリーを生成
	 *
	 * @param categoryCode  カテゴリーコード
	 * @param category  カテゴリー
	 */
	private CategoryEnum(String categoryCode, String category) {
		this.categoryCode = categoryCode;
		this.category = category;
	}

	/**
	 * カテゴリーコードを取得
	 *
	 * @return カテゴリーコード
	 */
	public String getCategoryCode() {
		return this.categoryCode;
	}

	/**
	 * カテゴリー名を取得
	 *
	 * @return カテゴリー名
	 */
	public String getCategory() {
		return this.category;
	}

	/**
	 * コードを基にカテゴリーを取得
	 *
	 * @param カテゴリーコード
	 * @return カテゴリー
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