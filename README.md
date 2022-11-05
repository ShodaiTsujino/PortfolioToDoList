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

起動後以下にアクセスすることでログイン画面に遷移し、ログイン後にTOPページへ移動後各CRUD処理が可能となります。

* [http://localhost:8080/login](http://localhost:8080/login)
![todoList_preview](https://user-images.githubusercontent.com/105256640/199258838-9e7c425e-53d7-4df3-87f6-9d56cde18dff.gif)

********
# 作成者
- 辻野　翔大
- 未来のカタチ
- s16h90o@gmail.com
