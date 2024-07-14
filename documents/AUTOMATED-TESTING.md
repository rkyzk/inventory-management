# Automated Testing

## 1. Registration Controller<br>

|No.|Tested features                          |Pass/Fail|Date        |
|:--|:----------------------------------------|:--------|:-----------|
| 1 |Method getProductRegistration will get product registration page.|P        |2024/7/13|
| 2 |validationErrorsReturnsRegistrationPage. |P        |2024/7/13   |
| 3 |Method postProduct will redirect to the list page if the input data is valid.|P        |2024/7/13|
| 4 |Message 'The product has been registered' will be displayed on the list page upon successful registration.|P        |2024/7/13   |

[Link to the image](./images/automated-testing/automated-testing-regController.jpg)

**The following tests are covered in the manual testing**
- If an image is uploaded, the image is stored in the AWS S3bucket<br>
- If an image is uploaded, the image name is stored in the DB.
- If an image is uploaded, the image path is stored in the DB.
- Product data is stored in the DB.

**Tests on Validation**
|No.|Tested Feature/Validation|Field|input|Expected Results              |Pass/Fail|Image|Date  |
|:--|:----------------|:----|:----|:-----------------------------|:--------|:----|:-----|
| 1 |no errors show no validation |--|deafult|validation passes|P      |--   |2024/7/13|
| 2 |NotBlank         |name |null |validation error|P   |--   |2024/7/13 |
| 3 |max size=40      |name |40 characters|validation passes|P      |--   |2024/7/13 |
| 4 |max size=40      |name |41 characters|validation error|P        |--   |2024/7/13 |
| 5 |min=1, max =9999 |qty  |1, 9999|validation passes|P     |--   |2024/7/13 |
| 6 |min=1, max =9999 |qty  |0    |validation error|P    |--  |2024/7/13   |
| 7 |min=1, max =9999 |qty  |10000|validation error|P    |--  |2024/7/13   |
| 8 |NotNull          |price|null|validation error|P      |--   |2024/7/13 |
| 9 |DecimalMin=0.00  |price|0.00|validation passes|P      |--   |2024/7/13 |
| 10|DecimalMin=0.00  |price|-0.50|validation error|P      |--   |2024/7/13 |
| 11|Dig›its constraint|price|12345.01|validation passes|P        |--   |2024/7/13 |
| 12|Digits constraint|price|123456, 12345.123|validation error|P      |-- |2024/7/13|
| 13|min=0, max =99999|stock|0, 99999|validation passes|P   |-- |2024/7/13 |
| 14|min=0, max =99999|stock|-1   |validation error|P        |--   |2024/7/13   |
| 15|min=0, max =99999|stock|100000|validation error|P        |--   |2024/7/13   |
| 16|max = 200        |description|200 chars|validation passes|P   |-- |2024/7/13 |
| 17|max = 200        |description|201 chars|validation error|P   |-- |2024/7/13 |

[Link to the image](./images/automated-testing/automated-testing-validation.jpg)

3. Product List Page

4. Update Page

5. Service class