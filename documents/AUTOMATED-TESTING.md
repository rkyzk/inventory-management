# Automated Testing
## 1. Registration Controller<br>

|No.|Tested features                          |Pass/Fail|Image|Date        |
|:--|:----------------------------------------|:--------|:----|:-----------|
| 1 |Method getProductRegistration will get product registration page.|P        |--   |2024/4/16   |
| 2 |validationErrorsReturnsRegistrationPage. |P        |--   |2024/4/16   |
| 3 |Method postProduct will redirect to the list page if the input data is valid.|P        |--   |2024/4/16   |
| 4 |Message 'The product has been registered' will be displayed on the list page upon successful registration.|P        |--   |2024/4/16   |

**The following tests are covered in the manual testing**
- If an image is uploaded, the image is stored in the AWS S3bucket<br>
(the bucket name is specified in 'application-local.properties'.)
- If an image is uploaded, the image name is stored in the DB.
- If an image is uploaded, the image path is stored in the DB.
- Product data is stored in the DB.

**Tests on Validation**
|No.|Tested Validation|Field|input|Expected Results              |Pass/Fail|Image|Date  |
|:--|:----------------|:----|:----|:-----------------------------|:--------|:----|:-----|
| 4 |NotBlank         |name |null |validation error|P   |--   |2024/4/16 |
| 5 |max size=40      |name |40 characters|validation passes|P      |--   |2024/4/16 |
| 6 |max size=40      |name |41 characters|validation error|P        |--   |2024/4/16 |
| 7 |max size=30      |manufacturer|30 characters|validation passes|P  |--  |2024/4/16 |
| 8 |max size=30      |manufacturer|31 characters|validation error|P  |--   |2024/4/16 |
| 9 |min=1, max =9999 |qty  |1, 9999|validation passes|P     |--   |2024/4/16 |
| 10|min=1, max =9999 |qty  |0    |validation error|P    |--  |2024/4/16   |
| 10|min=1, max =9999 |qty  |10000|validation error|P    |--  |2024/4/16   |
| 11|NotNull          |price|null|validation error|P      |--   |2024/4/16 |
| 12|DecimalMin=0.00  |price|0.00|validation passes|P      |--   |2024/4/16 |
| 13|DecimalMin=0.00  |price|-0.50|validation error|P      |--   |2024/4/16 |
| 14|Digits constraint|price|12345.01|validation passes|P        |--   |2024/4/16 |
| 15|Digits constraint|price|123456, 12345.123|validation error|P      |-- |2024/4/16|
| 16|min=0, max =99999|stock|0, 99999|validation passes|P   |-- |2024/4/16 |
| 17|min=0, max =99999|stock|-1   |validation error|P        |--   |2024/4/16   |
| 17|min=0, max =99999|stock|100000|validation error|P        |--   |2024/4/16   |
| 18|max = 200        |description|200 chars|validation passes|P   |-- |2024/4/20 |
| 19|max = 200        |description|201 chars|validation error|P   |-- |2024/4/16 |

| 22|FileSize         |multipartFile|1024000, 819200|validation passes|P  |-- |2024/4/20|
| 23|FileSize         |multipartFile|819201|validation error|P  |-- ||
| 24|FileName         |multipartFile|30 chars|validation passes|P  |-- |2024/4/20|
| 25|FileName         |multipartFile|31 chars|validation error|P  |-- |2024/4/20|