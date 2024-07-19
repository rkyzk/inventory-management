# Automated Testing

## 1. Registration Controller<br>

|No.|Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |Method getProductRegistration will return product registration page.|P        |2024/7/13|
| 2 |validationErrorsReturnsRegistrationPage. |P        |2024/7/13   |
| 3 |Method postProduct will redirect to the list page if the input data is valid.|P        |2024/7/13|
| 4 |Message 'The product has been registered' will be displayed on the list page upon successful registration.|P        |2024/7/13   |

[Link to the image](./images/automated-testing/automated-testing-regController.jpg)

**The following tests are covered in the manual testing**
- If an image is uploaded, the image is stored in the AWS S3bucket<br>
 --> **See Manual Testing 1. Registration Page Test No.1-3-1**
- If an image is uploaded, the image name is stored in the DB.<br>
 --> **See Manual Testing 1. Registration Page Test No.1-2-10**
- If an image is uploaded, the image path is stored in the DB.<br>
 --> **See Manual Testing 1. Registration Page Test No.1-2-11**
- Product data is stored in the DB.<br>
 --> **See Manual Testing 1. Registration Page Test No.1-2-1〜14**

### Tests on Validation
|No.|Tested Feature/Validation|Field|input|Expected Results              |Pass/Fail|Date  |
|:--|:----------------|:----|:----|:-----------------------------|:--------|:-----|
| 1 |no errors show no validation |--|deafult|validation passes|P      |2024/7/13|
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

** getProductList method **
|No.|Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |Method getProductList will get product list page. |P        |2024/7/16|
| 2 |model attribute 'prodList' holds the right value. |P        |2024/7/16|
| 3 |model attribute 'itemCount' holds the right value.|P        |2024/7/16|
| 4 |model attribute 'endpoint' holds the right value. |P        |2024/7/16|

** deleteProduct method **
|No.|Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |retVal = 1 sets delete success message to attribute 'message.'|P    |2024/7/16|
| 2 |retVal = 0 sets delete error message to attribute 'message.'|P    |2024/7/16|
| 4 |model attribute 'prodList' holds the right value. |P        |2024/7/16|
| 5 |model attribute 'itemCount' holds the right value.|P        |2024/7/16|
| 6 |model attribute 'endpoint' holds the right value. |P        |2024/7/16|
| 7 |The deleteProductMethod will return the list page.|P        |2024/7/16|

## 3. Update Controller

** getUpdate method **
|No.|Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |Method getUpdate will return update page.|P        |2024/7/19  |
| 2 |model attribute 'product' holds the right value. |P        |2024/7/19|
| 3 |model attribute 'endpoint' holds the right value. |P        |2024/7/19|

** postUpdate method **
|No.|Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |Validation error returns update page.    |P        |2024/7/19   |
| 2 |After update success update success message will be stored in msg.|P        |2024/7/19|
| 3 |If update operation fails, update error message will be stored in msg.|P        |2024/7/19|
| 4 |If vaidation passes, redirected to list page.|P        |2024/7/19|

5. Service class