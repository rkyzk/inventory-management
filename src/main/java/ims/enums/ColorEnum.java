package ims.enums;


/**
 * 色のEnum
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
public enum ColorEnum {
	/**
	 * 1: 赤
	 */
	RED(1, "赤"),
	/**
	 * 2: 黄
	 */
	YELLOW(2, "黄"),
	/**
	 * 3: オレンジ
	 */
	ORANGE(3, "オレンジ"),
	/**
	 * 4: ピンク
	 */
	PINK(4, "ピンク"),
	/**
	 * 5: 白
	 */
	WHITE(5, "白"),
	/**
	 * 6: 紫
	 */
	PURPLE(6, "紫"),
	/**
	 * 7: 青
	 */
	BLUE(7, "青");

	/** 色コード */
	private int colorCode;

	/** 色 */
	private String color;

	/**
	 * 色項目を生成
	 *
	 * @param colorCode  色コード
	 * @param color  色
	 */
	private ColorEnum(int colorCode, String color) {
		this.colorCode = colorCode;
		this.color = color;
	}

	/**
	 * 色コードを取得
	 *
	 * @return 色コード
	 */
	public int getColorCode() {
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
	public static ColorEnum getValueByCode(int colorCode) {
		for (ColorEnum color : ColorEnum.values()) {
			if (color.getColorCode() == colorCode) {
				return color;
			}
		}
		return null;
	}
}