if not exists create database gutenberg;

drop table if exists books;
create table books
(
id 		int,
title 	varchar(100),
primary key (id)
);

drop table if exists cities;
create table cities
(
name 	varchar(100),
lat		Decimal(10,8),
`long`	Decimal(11,8),
primary key (name)
);