# PortfolioToDoList
##Tsujino's portfolio

#学習用のポートフォリオ
- 概要
  - 一般的なToDoリストの登録・更新をすることができるサイトです。

## 使用技術

- 開発言語
  - Java

- プロジェクト管理ツール
  - Maven
  
- 使用フレームワーク
  - Java8
  - SpringBoot(Ver2.6.3)
  - css/BootStrap(Ver24.5.3)
  - MySQL
  - MyBatis(Ver2.1.4)
  
- DB
  - MySQL
  
- view
  - Thymeleaf
  - HTML/css
  - Bootstrap

## 実装機能

********
【主要機能】
-  ログイン機能 
-  ログアウト機能
-  作業内容の登録・更新(作業内容、担当者、登録日、期限日、完了日)
-  作業内容の削除(論理削除)
-  作業一覧画面で検索
******
【未実装】
- リストページのページネーション機能
- 検索機能の細分化
- カレンダー機能
********

#起動

##Server起動

起動後以下にアクセスすることでログイン画面に遷移します。

* [http://localhost:8080/login](http://localhost:8080/login)
![image](https://user-images.githubusercontent.com/105256640/196357405-b2c06177-7fcf-4dc2-ab55-277153d7ff02.png)

ログイン後にToDoリストの一覧に遷移し、各CRUD処理に移動が可能になります。
* [http://localhost:8080/list](http://localhost:8080/list)
![image](https://user-images.githubusercontent.com/105256640/196356652-5736984e-8407-4b5c-8a45-e518a27e6949.png)

********
# 作成者
- 辻野　翔大
- 就労移行支援事業所 未来のカタチ
- s16h90o@gmail.com
