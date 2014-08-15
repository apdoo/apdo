--创建数据库设置 utf8 防止程序插入中文报错 
--create database bd default character set utf8 collate utf8_general_ci;

--用户表
CREATE TABLE user(
    id int,
	username char(20),
    password char(20),
	datetime char(20),
	signdate char(20),
	type char(2),
	flag char (8)
);
--添加用户表id主键自增 
alter table user modify id int auto_increment primary key;
--留言板表
CREATE TABLE messagebox(
    id int,
	username varchar(20),
	datetime char(20),
	message varchar (300)
);
alter table messagebox modify id int auto_increment primary key;
 
------------------------下面的暂时不用添加
--激活码表
CREATE TABLE accode(
    id int,
	code char(8),
	type char(4)
);
--添加激活码表id主键自增 
alter table accode modify id int auto_increment primary key;

--签到记录表
CREATE TABLE signlog(
    id int,
	username char(20),
	signdate char(20)
);
alter table signlog modify id int auto_increment primary key;