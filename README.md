# 在庫管理システム

デプロイしたプロジェクト：

#### 概要
球根を販売する店で商品データを管理するシステム。

中〜大きい画面で使用する想定のためスクリーンサイズ幅
1000pxまでレスポンシブで対応



#### 主要機能
- 商品データの登録、更新、削除
- 商品一覧を照会
- 商品データをカテゴリーと色でフィルター

#### その他の機能
- 入力チェック（登録、更新画面）
- 処理完了・エラーメッセージ表示
- 削除前に確認ダイアログを表示
- パジネーション（一覧画面）

#### 使用言語、フレームワーク、DB、
- Java, JavaScript, HTML/CSS, SQL
- Spring Boot
- PostgreSQL

## ライブラリ
- MyBatis
- lombak

### 画面・要素別詳細
1. ヘッダー（全ページ共通）
ロゴ、ブランド名、リンク（登録画面、商品リスト）

2. 登録画面</br>
＜ヘッダー＞</br>
＜入力項目＞</br>
a.商品名
b.カテゴリー
c.色
d.値段
e.在庫数
f.商品説明
g.画像
＜ボタン＞
登録：商品データを登録する。postProductを呼び出す。
クリア

#### 修正したバグ
1. 画像をしない商品を保存し画像以外のデータを更新してから削除しようとすると、
  「画像が削除されませんでした」のメッセージが表示され、画像が保存されていたかのように
   処理されていた。
   対処：
   ListController deleteProductメソッドのS3bucketnの画像削除処理の条件を下記のとおり変更
   if (product.getImageName() != null)
     => if (product.getImageName() != null && product.getImageName().length() != 0)


### データ

### Each Page in Detail

1.Product Registration page<br>
2.Product List page<br>
3.Update page<br>

### Deployment Process

### Junitテスト
テスト項目<br>
./documents/AUTOMATED-TESTING.md

### 手動テスト
テスト項目<br>
./documents/MANUAL-TESTING.md

### Credit

- The logo was made at [AI Logo Generator](https://www.design.com/s/logo?var=ai-logo-generator&code=25OFFSEM&utm_adgroup=aie&utm_keyword=ai+logo&utm_network=s&msclkid=b749abc8e8f218e63ddbcd55e7f16b09&utm_source=bing&utm_medium=cpc&utm_campaign=ROW:+03.+Generic:+Logos&utm_term=ai+logo&utm_content=ai[E]).

- The favicon was taken from:<br>
https://icons8.com/icons/set/favicon-flower

- Font 'Montserrat' for the headings was imported from Google Fonts.

