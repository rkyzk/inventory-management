# 在庫管理システム

デプロイしたプロジェクト：

#### 概要
#### バグ
1. 画像をしない商品を保存し画像以外のデータを更新してから削除しようとすると、
  「画像が削除されませんでした」のメッセージが表示され、画像が保存されていたかのように
   処理されていた。
   対処：
   ListController deleteProductメソッドのS3bucketnの画像削除処理の条件を下記のとおり変更
   if (product.getImageName() != null)
     => if (product.getImageName() != null && product.getImageName().length() != 0)
    



### Overview
With this Inventory Management System, shop owners can register/update/delete product data and
look at the list of product data.
The app comes with functions such as input validation, delete confirmation,
success/error messages upon registering, updating and deleting products.

### Main Technologies Used
HTML5, CSS3, Java (Spring Boot), Bootstrap5, jquery, PostgreSQL

### Functions
**Main functions**
- Register product data including images
- Display Product List
- Update products data
- Delete products

**Supplementary functions**
- Display success messages when products have been registered, updated or deleted.
- Validate input data while registering and updating products and show error messages
  if validation fails.
- Display a confirmation modal before deleting products.
- correct price format if the decimal digits are not complete (register & update pages)


### User Stories

*As user I...*

|Nr.| User Stories                               | How they are achieved |
| - | :----------------------------------------- | --------------------- |
| 1 | can register product information.          | register product page |
| 2 | can upload product image.                  | image can be uploaded on register & update pages |
| 3 | get notified if input data is inappropriate| validation messages  |
| 4 | can look at the product list               | product list page     |
| 5 | can find out how many products are registered. | total number of items is displayed above the table on the right side on product list page     |
| 6 | can update product information             | update product page   |
| 7 | can delete product                         | product list page     |
| 8 | get confirmation before deleting product   | a confirmation dialog will show up when user attempts to delete products.   |
| 9 | can easily navigate through all pages      | navigation links, update buttons on list page |

### Data Modeling

### Each Page in Detail

1.Product Registration page<br>
2.Product List page<br>
3.Update page<br>

### Deployment Process

### Automated Testing
Automated Testing is documented [here](./documents/AUTOMATED-TESTING.md).

### Manual Testing
Manual Testing is documented [here](./documents/MANUAL-TESTING.md).

### Bugs

### Credit

- The logo was made at [AI Logo Generator](https://www.design.com/s/logo?var=ai-logo-generator&code=25OFFSEM&utm_adgroup=aie&utm_keyword=ai+logo&utm_network=s&msclkid=b749abc8e8f218e63ddbcd55e7f16b09&utm_source=bing&utm_medium=cpc&utm_campaign=ROW:+03.+Generic:+Logos&utm_term=ai+logo&utm_content=ai[E]).

- The favicon was taken from:<br>
https://icons8.com/icons/set/favicon-flower

- Font 'Montserrat' for the headings was imported from Google Fonts.

