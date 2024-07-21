# Manual Testing

Machine: MacBook Air Retina, 13-inch, 2019
MacOS: Ventura 13.2.1
Browser: Google Chrome

1. Registration Page
1-1 Check the layout, link and clear button.

|Nr|Feature tested|procedure|Expected Results|Pass/Fail|image|Date|
|:-|:-------------|:--------------------|:-------------|:--|:--|:---|
|1 |layout        |check if all elements on the page are displayed fine for screen sizes from 1000px to 1300px|All elements are displayed fine in the specified range of screen sizes.|p|[image](./images/manual-testing/1-1-1.jpg)|2024/7/14|
|2 |link          |click the link to product list page|redirected to product list page|p|[image](./images/manual-testing/1-1-2.png)|2024/7/14|

1-2 Test if the product data will be inserted into DB.<br>
Procedure: Enter the following product data, and click 'register.'
Enter the standard data written below unless specified otherwise:

*Standard data*
|name|category|qty. per pack|price|stock|description|img|
|:--------|:--------|:--------|:-|:-------|:---|:--------|
|test1|tulips|20|10.50|10|(leave blank)|don't upload any image|

|Nr|Feature tested|name|category|qty. per pack|price|stock|descrip|img|expected result|Pass/Fail|image|Date|
|:-|:----------|:---------|:---|:--|:---|:--|:-------|:--|:--------|:--|:--|:--|
|1 | name||||||||"test1" is inserted in the column name.|P|[image](./images/manual-testing/1-2-1.jpg)<br>[image](./images/manual-testing/1-2.jpg)|2024/7/14|
|2 | category: tulips||||||||"0" is inserted in the column category.|P|see image for test Nr.1|2024/7/14|
|3 | category: crocus|test2|crocus||||||"1" is inserted in the column category.|P|[image](./images/manual-testing/1-2-3.jpg)<br>[image](./images/manual-testing/1-2.jpg)|2024/7/14|
|4 | category: hyacinth|test3|hyacinth||||||"2" is inserted in the column category.|P|[image](./images/manual-testing/1-2-4.jpg)<br>[image](./images/manual-testing/1-2.jpg)|2024/7/14|
|5 | category: others|test4|others||||||"3" is inserted in the column category.|P|[image](./images/manual-testing/1-2-5.jpg)<br>[image](./images/manual-testing/1-2.jpg)|2024/7/14|
|6 | quantity||||||||20 is inserted in the column quantity.|P|see image for test Nr.1|2024/7/14|
|7 | price||||||||10.50 is inserted in the column price.|P|see image for test Nr.1|2024/7/14|
|8 | stock||||||||10 is inserted in the column stock.|P|see image for test Nr.1|2024/7/14|
|9 | description|test9|||||test description|don't upload any image| "test description" is inserted in the column description.|P|see image for test Nr.1|2024/7/14|
|10 | image name|test10||||||upload 'test.jpg'|'test.jpg + (timestamp)'  will be inserted in the column image_name. |P|[image](./images/manual-testing/1-2-10.jpg)<br>[image](./images/manual-testing/1-2-price_stock.jpg)|2024/7/14|
|11 | image path|||||||upload 'test.jpg'|'tulips/test.jpg + (timestamp)' will be inserted in the column image_path. |P|see image for test Nr.10|2024/7/14|
|12 | created_at||||||||The time the product was registered will be inserted in the column created_at  |P|see image for test Nr. 1|2024/7/14|
|13 | updated_at||||||||The time the product was registered will be inserted in the column updated_at  |P|see image for test Nr. 1|2024/7/14|
|14 | deleted_at||||||||The deleted_at will be null.|P|see image for test Nr. 1|2024/7/14|

1-3 Test that image will be uploaded to AWS S3 bucket

|Nr|Feature tested|procedure|expected result|Pass/Fail|image|Date|
|:-|:-------|:-----------------|:---------------|:---|:--|:---|
|1 |image upload|Check if the image "test.jpg", which was uploaded in test Nr. 1-2-10, is stored in s3bucket. |Image "test + (timestamp).jpg" is uploaded in S3 bucket|P|[image](./images/manual-testing/1-3-1.png)|2024/7/14|


1-4 Test other features on Registration page

|Nr|Feature tested|procedure|expected result|Pass/Fail|image|Date|
|:-|:------------|:----------------------------|:--------------|:--|:---|:--|
|1|clear button|enter "crocus" for category, "test description" for description, enter standard data, upload test.jpg and click 'clear'|all entries will be cleared.|P|[image](./images/manual-testing/1-4-1.jpg)<br>[image](./images/manual-testing/1-4-1-2.jpg)|2024/7/15|
|2|image upload error message|Change 'return filePath' to 'return null;' in the uploadImg method in ImageUploadService class. enter standard data, upload test.jpg and click 'register'|Redirected to list page. Message IMGUPLERR will be displayed.|P|[image](./images/manual-testing/1-4-2.jpg)|2024/7/20|
|3|Registration error message|Change insertProduct method in ProductService class so that it returns 0 in all cases. Enter standard data and click 'register'|Redirected to list page. Message REGERR will be displayed.|P|[image](./images/manual-testing/1-4-3.jpg)|2024/7/20|2024/7/20|

2. List Page
2-1 Check the layout and link

|Nr|Feature tested|procedure|Expected Results|Pass/Fail|image|Date|
|:-|:-------------|:---------------------------|:-------------|:--|:--|:---|
|1 |layout        |check if all elements on the page are displayed fine for screen sizes between 1000px and 1300px|All elements are displayed fine in the specified range of screen sizes.|p|[image](./images/manual-testing/2-1-1.jpg)<br>[image](./images/manual-testing/2-1-1-2.jpg)|2024/7/20|
|2 |link          |click the link to registration page|redirected to registration page|p|[image](./images/manual-testing/2-1-2.png)|2024/7/20|

2-2 Check other features

|Nr|Feature tested|procedure|Expected Results|Pass/Fail|image|Date|
|:-|:-------------|:---------------------------|:-------------|:--|:--|:---|
|1 |image |Make sure there're at least one product with an image and one without in the DB. |The image is displayed fine, and image 'No image available' is displayed for the products without images.|p|[image](./images/manual-testing/2-1-1-2.jpg)|2024/7/20|
|2 |edit icon |click the edit icon of the 1st product in the table|redirected to update page of the product|p|[image](./images/manual-testing/2-2-2.jpg)|2024/7/20|
|3 |delete icon |click the delete icon of the 1st product in the table|Delete confirmation dialog will show up.|p|[image](./images/manual-testing/2-2-3.jpg)|2024/7/20|

3. Update Page
3-1 Check the layout and link

|Nr|Feature tested|procedure|Expected Results|Pass/Fail|image|Date|
|:-|:-------------|:---------------------------|:-------------|:--|:--|:---|
|1 |layout        |check if all elements on the page are displayed fine for screen sizes between 1000px and 1300px|All elements are displayed fine in the specified range of screen sizes.|p|[image](./images/manual-testing/3-1-1.jpg)<br>[image](./images/manual-testing/3-1-1-2.jpg)|2024/7/21|
|2 |link          |click the link to registration page|redirected to registration page|p|[image](./images/manual-testing/3-1-2.jpg)|2024/7/21|
|3 |link          |click the link to list page|redirected to list page|p|[image](./images/manual-testing/3-1-3.jpg)|2024/7/21|

3-2 Test if the correct data is displayed after initial load of the page<br>
Procedure: On registration page register the following product.<br>
On list page click the edit icon of the product.

*product data*
|--|name|category|qty. per pack|price|stock|description|img|
|:-|:--------|:--------|:--------|:-|:-------|:---|:--------|
|data to register|test1|tulips|20|10.50|10|test description|test.jpg|

|Nr|Feature tested|procedure|Expected Results|Pass/Fail|image|Date|
|:-|:-------------|:---------------------------|:-------------|:--|:--|:---|
|1 |initial data on update page|Check if all values are displayed correctly in comparison to the DB|All values are displayed correctly.|p|[image](./images/manual-testing/3-2-1.jpg)<br>[image](./images/manual-testing/3-2-1-2.jpg)|2024/7/21|
|2 |Current image file|Check the statement 'Current image file; 'test.jpg' and the checkbox for removing image are shown in the screen shot taken in the previous test.|The statement is shown.|p|see the image for the previous test|2024/7/21|
|3 |Checkbox for removing image|Display the same update page as 3-2-1. Check the checkbox and click 'update'|The image name & path are removed from the DB.|p|[image](./images/manual-testing/3-2-3.jpg)<br>[image](./images/manual-testing/3-2-3-2.jpg)|2024/7/21|
- Add again test.jpg after test 3-2-3.

3-3 Test if the product data in the DB will be updated.<br>
Procedure: On the list page click edit icon of the product. On update page, update the data and click update.

*data to update*
|name|category|qty. per pack|price|stock|description|img|
|:--------|:--------|:--------|:-|:-------|:---|:--------|
|test updated|crocus|25|20.00|15|test description updated|test-update.jpg|

|Nr|Feature tested|procedure|Expected Results|Pass/Fail|image|Date|
|:-|:-------------|:---------------------------|:-------------|:--|:--|:---|
|1 |update function|check if all items are updated correctly in the DB|All items are updated correctly.|p|[image](./images/manual-testing/3-3-1.jpg)<br>[image](./images/manual-testing/3-3-1-2.jpg)|2024/7/21|

3-4 Test other features.<br>

|Nr|Feature tested|procedure|Expected Results|Pass/Fail|image|Date|
|:-|:-------------|:---------------------------|:-------------|:--|:--|:---|
|1 |cancel button|display the updated page of the product updated in the previous test.|change the values of all entries, and then cancel.|Check that none of the data was updated.|p|[image](./images/manual-testing/3-4-1.jpg)<br>[image](./images/manual-testing/3-4-1-2.jpg)|2024/7/21|

4. Tests on JavaScript

4-1 Register Page

|Nr|Feature tested|procedure|Expected Results|Pass/Fail|image|Date|
|:-|:-------------|:---------------------------|:-------------|:--|:--|:---|
|1 |format quantity|enter '01' for quantity and press tab key|the value will be '1'|p|[image](./images/manual-testing/4-1-1.jpg)<br>[image](./images/manual-testing/4-1-1-2.jpg)|2024/7/21|
|2 |format stock|enter '01' for stock and press tab key|the value will be '1'|p|[image](./images/manual-testing/4-1-2.jpg)<br>[image](./images/manual-testing/4-1-2-2.jpg)|2024/7/21|
|3 |format price(dicimal digits will be complemented)|enter '100' for price and press tab key|The value will be '100.00'|p|[image](./images/manual-testing/4-1-3.jpg)<br>[image](./images/manual-testing/4-1-3-2.jpg)|2024/7/21|
|4 |format price(dicimal digits will be complemented)|enter '100.1' for price and press tab key|The value will be '100.10'|p|[image](./images/manual-testing/4-1-4.jpg)<br>[image](./images/manual-testing/4-1-4-2.jpg)|2024/7/21|
|5 |format price(leading 0s will be removed)|enter '00100.10' for price and press tab key|The value will be '100.10'|p|[image](./images/manual-testing/4-1-5.jpg)<br>[image](./images/manual-testing/4-1-5-2.jpg)|2024/7/21|

4-2 List Page

|Nr|Feature tested|procedure|Expected Results|Pass/Fail|image|Date|
|:-|:-------------|:---------------------------|:-------------|:--|:--|:---|
|1 |Flash message disappears after seconds|||p|[image](./images/manual-testing/3-3-1.jpg)<br>[image](./images/manual-testing/3-3-1-2.jpg)|2024/7/21|

4-3 Update Page

|Nr|Feature tested|procedure|Expected Results|Pass/Fail|image|Date|
|:-|:-------------|:---------------------------|:-------------|:--|:--|:---|
|1 |formate quantity|||p|[image](./images/manual-testing/3-3-1.jpg)<br>[image](./images/manual-testing/3-3-1-2.jpg)|2024/7/21|
|2 |formate stock|||p|[image](./images/manual-testing/3-3-1.jpg)<br>[image](./images/manual-testing/3-3-1-2.jpg)|2024/7/21|
|2 |formate price|||p|[image](./images/manual-testing/3-3-1.jpg)<br>[image](./images/manual-testing/3-3-1-2.jpg)|2024/7/21|

