--�������ݿ����� utf8 ��ֹ����������ı��� 
--create database bd default character set utf8 collate utf8_general_ci;

--�û���
CREATE TABLE user(
    id int,
	username char(20),
    password char(20),
	datetime char(20),
	signdate char(20),
	type char(2),
	flag char (8)
);
--����û���id�������� 
alter table user modify id int auto_increment primary key;
--���԰��
CREATE TABLE messagebox(
    id int,
	username varchar(20),
	datetime char(20),
	message varchar (300)
);
alter table messagebox modify id int auto_increment primary key;
 
------------------------�������ʱ�������
--�������
CREATE TABLE accode(
    id int,
	code char(8),
	type char(4)
);
--��Ӽ������id�������� 
alter table accode modify id int auto_increment primary key;

--ǩ����¼��
CREATE TABLE signlog(
    id int,
	username char(20),
	signdate char(20)
);
alter table signlog modify id int auto_increment primary key;