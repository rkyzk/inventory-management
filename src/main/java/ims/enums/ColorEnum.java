package ims.enums;


/**
 * 色のEnum
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
public enum ColorEnum {
	/*
	 * 0: 赤
	 */
	RED("0", "赤"),
	/**
	 * 1: 黄
	 */
	YELLOW("1", "黄"),
	/**
	 * 2: オレンジ
	 */
	ORANGE("2", "オレンジ"),
	/**
	 * 3: ピンク
	 */
	PINK("3", "ピンク"),
	/**
	 * 4: 白
	 */
	WHITE("4", "白"),
	/**
	 * 5: 紫
	 */
	PURPLE("5", "紫"),
	/**
	 * 6: 青
	 */
	BLUE("6", "青");

	/** カラーコード */
	private String colorCode;

	/** 色 */
	private String color;

	/**
	 * 色項目を生成
	 *
	 * @param colorCode  色コード
	 * @param color  色
	 */
	private ColorEnum(String colorCode, String color) {
		this.colorCode = colorCode;
		this.color = color;
	}

	/**
	 * 色コードを取得
	 *
	 * @return 色コード
	 */
	public String getColorCode() {
		return this.colorCode;
	}

	/**
	 * 色を取得
	 *
	 * @return 色
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * コードを基に色を取得
	 *
	 * @param 色コード
	 * @return 色
	 */
	public static ColorEnum getValueByCode(String colorCode) {
		for (ColorEnum color : ColorEnum.values()) {
			if (color.getColorCode().equals(colorCode)) {
				return color;
			}
		}
		return null;
	}
}