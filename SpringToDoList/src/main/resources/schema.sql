/*ユーザーマスタ*/
CREATE TABLE IF NOT EXISTS m_user (
    user_id int AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) not null,
    password VARCHAR(80) not null
);

/* 作業テーブル */
CREATE TABLE IF NOT EXISTS t_task(
id int AUTO_INCREMENT PRIMARY KEY,
user_id int not null,
content VARCHAR(100) not null,
created_date date not null,
end_date date not null,
comlete_date date,
delete_flg
);