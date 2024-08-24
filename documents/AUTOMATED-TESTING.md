# 自動テスト

## 1. Registration Controller<br>

|No |テストした機能                       |Pass/Fail|日付       |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |メソッド「getProductRegistration」が登録ページを表示すること|P        |2024/7/13|
| 2 |入力チェックでエラーがある際は登録ボタン押下の際、登録画面が表示されること。 |P        |2024/7/13   |
| 3 |メソッド「postProduct」が入力エラーがない場合一覧を表示すること|P        |2024/7/13|
| 4 |登録成功時「The product has been registered」のメッセージが設定されること。|P        |2024/7/13   |

[エビデンス](./images/automated-testing/automated-testing-regController.jpg)

### 入力チェックのテスト
|No |バリデーション|フィールド|入力値|想定結果              |Pass/Fail|日付  |
|:--|:----------------|:----|:----|:-----------------------------|:--------|:-----|
| 1 |エラーがない場合バリデーションエラーがないこと |--|デフォルト値 |バリデーションエラーがないこと|P      |2024/7/13|
| 2 |NotBlank         |name |null |エラーとなること|P   |2024/7/13 |
| 3 |max size=40      |name |40 characters|エラーとならないこと|P      |2024/7/13 |
| 4 |max size=40      |name |41 characters|エラーとなること|P      |2024/7/13 |
| 5 |min=1, max =9999 |qty  |1, 9999|エラーとならないこと|P     |2024/7/13 |
| 6 |min=1, max =9999 |qty  |0    |エラーとなること|P    |2024/7/13   |
| 7 |min=1, max =9999 |qty  |10000|エラーとなること|P    |2024/7/13   |
| 8 |NotNull          |price|null|エラーとなること|P     |2024/7/13 |
| 9 |DecimalMin=0.00  |price|0.00|エラーとならないこと|P     |2024/7/13 |
| 10|DecimalMin=0.00  |price|-0.50|エラーとなること|P     |2024/7/13 |
| 11|Digits constraint|price|12345.01|エラーとならないこと|P      |2024/7/13 |
| 12|Digits constraint|price|123456, 12345.123|エラーとなること|P      |2024/7/13|
| 13|min=0, max =99999|stock|0, 99999|エラーとならないこと|P    |2024/7/13 |
| 14|min=0, max =99999|stock|-1   |エラーとなること|P      |2024/7/13   |
| 15|min=0, max =99999|stock|100000|エラーとなること|P     |2024/7/13   |
| 16|max = 200        |description|200 chars|エラーとならないこと|P    |2024/7/13 |
| 17|max = 200        |description|201 chars|エラーとなること|P    |2024/7/13 |
| 18|size = 30        |file name|30 chars|エラーとならないこと|P    |2024/7/15 |
| 19|size = 31        |file name|31 chars|エラーとなること|P    |2024/7/15 |
| 20|file type        |file type|jpg|エラーとならないこと|P    |2024/7/15 |
| 21|file type        |file type|jpeg|エラーとならないこと|P    |2024/7/15 |
| 22|file type        |file type|png|エラーとならないこと|P    |2024/7/15 |
| 23|file type        |file type|gif|エラーとなること|P    |2024/7/15 |
| 24|file size        |file size|819200B|エラーとならないこと|P    |2024/7/15 |
| 25|file size        |file size|819201B|エラーとなること|P    |2024/7/15 |

[エビデンス](./images/automated-testing/automated-testing-validation.jpg)

## 2. ProductList Controller

**メソッド　getProductList**
|Nr |テスト項目                          |Pass/Fail|日付       |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |getProductListメソッドが一覧画面を表示すること |P        |2024/7/16|
| 2 |model attribute「prodList」に正しい値が設定されること |P        |2024/7/16|
| 3 |model attribute「itemCount」に正しい値が設定されること|P        |2024/7/16|
| 4 |model attribute「endpoint」に正しい値が設定されること |P        |2024/7/16|

**メソッド　deleteProduct method**
|Nr |Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |retVal = 1 のとき削除成功のメッセージが設定されること|P    |2024/7/16|
| 2 |retVal = 0 のとき削除失敗のメッセージが設定されること|P    |2024/7/16|
| 4 |model attribute 'prodList' に正しい値が設定されること |P        |2024/7/16|
| 5 |model attribute 'itemCount' に正しい値が設定されること|P        |2024/7/16|
| 6 |model attribute 'endpoint' に正しい値が設定されること |P        |2024/7/16|
| 7 |deleteProductMethodメソッドが一覧画面を表示すること|P        |2024/7/16|

## 3. Update Controller

**メソッドgetUpdate method**
|Nr |Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |getUpdate が更新画面を表示すること|P        |2024/7/19  |
| 2 |model attribute 'product' に正しい値が設定されること |P        |2024/7/19|
| 3 |model attribute 'endpoint'に正しい値が設定されること |P        |2024/7/19|

**メソッドpostUpdate **
|Nr |Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |入力エラーの際、更新画面が表示されること    |P        |2024/7/19   |
| 2 |更新時、更新成功のメッセージが表示されるよう設定されること|P        |2024/7/19|
| 3 |更新失敗の際、更新失敗のメッセージが表示されるよう設定されること|P        |2024/7/19|
| 4 |入力エラーがない場合一覧画面が表示されること|P        |2024/7/19|

[エビデンス](./images/automated-testing/update-controller.jpg)








# Automated Testing

## 1. Registration Controller<br>

|Nr |Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |Method getProductRegistration will return product registration page.|P        |2024/7/13|
| 2 |validationErrorsReturnsRegistrationPage. |P        |2024/7/13   |
| 3 |Method postProduct will redirect to the list page if the input data is valid.|P        |2024/7/13|
| 4 |Message 'The product has been registered' will be displayed on the list page upon successful registration.|P        |2024/7/13   |

[Link to the image](./images/automated-testing/automated-testing-regController.jpg)

### Tests on Validation
|Nr |Tested Feature/Validation|Field|input|Expected Results              |Pass/Fail|Date  |
|:--|:----------------|:----|:----|:-----------------------------|:--------|:-----|
| 1 |no errors show no validation |--|all default values |validation passes|P      |2024/7/13|
| 2 |NotBlank         |name |null |validation error|P   |2024/7/13 |
| 3 |max size=40      |name |40 characters|validation passes|P      |2024/7/13 |
| 4 |max size=40      |name |41 characters|validation error|P      |2024/7/13 |
| 5 |min=1, max =9999 |qty  |1, 9999|validation passes|P     |2024/7/13 |
| 6 |min=1, max =9999 |qty  |0    |validation error|P    |2024/7/13   |
| 7 |min=1, max =9999 |qty  |10000|validation error|P    |2024/7/13   |
| 8 |NotNull          |price|null|validation error|P     |2024/7/13 |
| 9 |DecimalMin=0.00  |price|0.00|validation passes|P     |2024/7/13 |
| 10|DecimalMin=0.00  |price|-0.50|validation error|P     |2024/7/13 |
| 11|Digits constraint|price|12345.01|validation passes|P      |2024/7/13 |
| 12|Digits constraint|price|123456, 12345.123|validation error|P      |2024/7/13|
| 13|min=0, max =99999|stock|0, 99999|validation passes|P    |2024/7/13 |
| 14|min=0, max =99999|stock|-1   |validation error|P      |2024/7/13   |
| 15|min=0, max =99999|stock|100000|validation error|P     |2024/7/13   |
| 16|max = 200        |description|200 chars|validation passes|P    |2024/7/13 |
| 17|max = 200        |description|201 chars|validation error|P    |2024/7/13 |
| 18|size = 30        |file name|30 chars|validation passes|P    |2024/7/15 |
| 19|size = 31        |file name|31 chars|validation error|P    |2024/7/15 |
| 20|file type        |file type|jpg|validation passes|P    |2024/7/15 |
| 21|file type        |file type|jpeg|validation passes|P    |2024/7/15 |
| 22|file type        |file type|png|validation passes|P    |2024/7/15 |
| 23|file type        |file type|gif|validation error|P    |2024/7/15 |
| 24|file size        |file size|819200B|validation passes|P    |2024/7/15 |
| 25|file size        |file size|819201B|validation error|P    |2024/7/15 |

[Link to the image](./images/automated-testing/automated-testing-validation.jpg)

## 2. ProductList Controller

**getProductList method**
|Nr |Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |Method getProductList will get product list page. |P        |2024/7/16|
| 2 |model attribute 'prodList' holds the right value. |P        |2024/7/16|
| 3 |model attribute 'itemCount' holds the right value.|P        |2024/7/16|
| 4 |model attribute 'endpoint' holds the right value. |P        |2024/7/16|

**deleteProduct method**
|Nr |Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |retVal = 1 sets delete success message to attribute 'message.'|P    |2024/7/16|
| 2 |retVal = 0 sets delete error message to attribute 'message.'|P    |2024/7/16|
| 4 |model attribute 'prodList' holds the right value. |P        |2024/7/16|
| 5 |model attribute 'itemCount' holds the right value.|P        |2024/7/16|
| 6 |model attribute 'endpoint' holds the right value. |P        |2024/7/16|
| 7 |The deleteProductMethod will return the list page.|P        |2024/7/16|

## 3. Update Controller

**getUpdate method**
|Nr |Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |Method getUpdate will return update page.|P        |2024/7/19  |
| 2 |model attribute 'product' holds the right value. |P        |2024/7/19|
| 3 |model attribute 'endpoint' holds the right value. |P        |2024/7/19|

**postUpdate method**
|Nr |Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |Validation error returns update page.    |P        |2024/7/19   |
| 2 |If update is successful, update success message will be stored in msg.|P        |2024/7/19|
| 3 |If update operation fails, update error message will be stored in msg.|P        |2024/7/19|
| 4 |If vaidation passes, redirected to list page.|P        |2024/7/19|

[Link to the image](./images/automated-testing/update-controller.jpg)
