/*ユーザーマスタ*/
INSERT INTO m_user (
    user_name,
    password
    )VALUES
('テスト1', '$2a$10$x3lV8FdwJcDx2VAuYBuVOOUCa64alZpGQEqPUgU5GXWdolA4uektm'),
('テスト2', '$2a$10$x3lV8FdwJcDx2VAuYBuVOOUCa64alZpGQEqPUgU5GXWdolA4uektm'),
('テスト3', '$2a$10$x3lV8FdwJcDx2VAuYBuVOOUCa64alZpGQEqPUgU5GXWdolA4uektm')
;

/* 作業テーブル */
INSERT INTO t_task(
user_id,
content,
created_date,
end_date,
complete_date,
delete_flg
)VALUES
('1','test1を実施する','2019-01-30','2019-01-30',null,0),
('2','test2の結果を報告する','2019-01-30','2019-01-30','2019-05-10',0),
('3','test3はどうなっているのか尋ねる','2019-01-30','2019-01-30','2019-05-10',0),
('2','100文字チェックあああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ','2019-01-30','2022-02-22',null,0)
('3','test3はどうなっているのか尋ねる','2019-01-30','2018-01-30','2019-05-10',0),
;