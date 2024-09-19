package ims.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ims.validation.FileNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * ファイル名の桁数を検証するアノテーションインターフェース.
 *
 * @author R.Yazaki
 * @version 1.0.0
 */
@Constraint(validatedBy = FileNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileName {

	/**
	 * ファイル名最大桁数を設定.
	 *
	 * @return 最大桁数
	 */
	int maxLength();

	/**
	 * エラーメッセージを生成
	 *
	 * @return エラーメッセージ
	 */
	String message() default "{EMSG101}";

	/**
	 * グループごとに異なる制約チェックを行う場合に利用する.
	 *
	 * @return デフォルトのグループ
	 */
	Class<?>[] groups() default {};

	/**
	 * チェック対象のオブジェクトにメタ情報を与えるための宣言.
	 *
	 * @return チェック対象オブジェクトのメタ情報
	 */
	Class<? extends Payload>[] payload() default {};
}