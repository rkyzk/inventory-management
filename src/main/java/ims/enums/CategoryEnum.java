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
	TULIPS(0, "チューリップ", "tulips"),
	/**
	 * 1:  クロッカス
	 */
	CROCUS(1, "クロッカス", "crocus"),
	/**
	 * 2: ヒヤシンス
	 */
	HYACINTH(2, "ヒヤシンス", "hyacinth"),
	/**
	 * 3: その他
	 */
	OTHERS(3, "その他", "others");

	/** カテゴリーコード */
	private int categoryCode;

	/** カテゴリー名 */
	private String category;
	
	/** 英語カテゴリー名 */
	private String categoryEn;

	/**
	 * カテゴリーを生成
	 *
	 * @param categoryCode  カテゴリーコード
	 * @param category  カテゴリー
	 * @param categoryEn 英語カテゴリー
	 */
	private CategoryEnum(int categoryCode, String category, String categoryEn) {
		this.categoryCode = categoryCode;
		this.category = category;
		this.categoryEn = categoryEn;
	}

	/**
	 * カテゴリーコードを取得
	 *
	 * @return カテゴリーコード
	 */
	public int getCategoryCode() {
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
	 * カテゴリー名を取得
	 *
	 * @return カテゴリー名
	 */
	public String getCategoryEn() {
		return this.categoryEn;
	}

	/**
	 * コードを基にカテゴリーを取得
	 *
	 * @param カテゴリーコード
	 * @return カテゴリー
	 */
	public static CategoryEnum getValueByCode(int categoryCode) {
		for (CategoryEnum category : CategoryEnum.values()) {
			if (category.getCategoryCode() == categoryCode) {
				return category;
			}
		}
		return null;
	}
}