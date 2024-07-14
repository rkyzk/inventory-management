# Manual Testing(new)

Machine: MacBook Air Retina, 13-inch, 2019
MacOS: Ventura 13.2.1
Browser: Google Chrome

1. Registration Page
1-1 Check the layout, link and clear button.
|Nr|Feature tested|procedure|Expected Results|Pass/Fail|image|Date|
|:-|:-------------|:----------------------------------------|:-------------|:--|:---|:-----|
|1|layout|check if all elements on the page are displayed fine for screen sizes between 1000px and 1300px|All elements are displayed fine in the specified range of screen sizes.|p|[image](./images/manual-testing/1-1-1.jpg)|2024/7/14|
|2|link|click the link to product list page|redirected to product list page|p|[image](./images/manual-testing/1-1-2.jpg)|2024/7/14|

1-2 Test that the product data will be inserted into DB.<br>
Procedure: Enter the following product data, and click 'register.'
Enter the standard data written below unless specified otherwise:

*Standard data*
|name|category|qty. per pack|price|stock|descrip|img|
|:--------|:--------|:--------|:-|:-------|:---|:--------|
|test1|tulips|20|10.50|10|(leave blank)|don't upload any image|

|Nr|Feature tested|name|category|qty. per pack|price|stock|descrip|img|expected result|Pass/Fail|image|Date|
|:-|:-------------------|:--------|:--------|:--|:-----|:--|:------------|:--|:--------|:--|:--|:--|:--|
|1 | name||||||||"test1" is inserted in the column name.|P|[image](./images/manual-testing/1-2-1.jpg)<br>[image](./images/manual-testing/1-2-1-2.jpg)|||
|2 | category: tulips||||||||"0" is inserted in the column category.||see image for test No.1||
|3 | category: crocus|test2|crocus|enter the standard data|||||"1" is inserted in the column category.||||
|4 | category: hyacinth|test3|hyacinth|enter the standard data|||||"2" is inserted in the column category.||||
|5 | category: others|test4|others|enter the standard data|||||"3" is inserted in the column category.||||
|6 | quantity||||||||20 is inserted in the column quantity.||||
|7 | price||||||||10.50 is inserted in the column price.||||
|8 | stock||||||||10 is inserted in the column stock.||||
|9 | description|test9|enter the standard data||||test description|don't upload any image| "test description" is inserted in the column description.||||
|10 | image name|test10|enter the standard data|||||upload 'test.jpg'|'test.jpg' will be inserted in the column image_name. ||||
|11 | image path|||||||upload 'test.jpg'|'tulips/test.jpg' will be inserted in the column image_path. ||||
|12 | created_at||||||||The time the product was registered will be inserted in the column created_at  ||||
|13 | updated_at||||||||The time the product was registered will be inserted in the column updated_at  ||||
|14 | deleted_at|s|||||||The deleted_at will be null.||||