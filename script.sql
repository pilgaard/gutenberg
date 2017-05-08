drop database if exists gutenberg;

CREATE DATABASE gutenberg
  DEFAULT CHARACTER SET utf8mb4;
  
use gutenberg;

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
geonameId			int,
`name` 				varchar(200),
asciiname			varchar(200),
alternatenames		varchar(10000),
lat					Decimal(10,8),
`long`				Decimal(11,8),
`country code`		char(2),			
population			bigint,
elevation			int,
dem					int,
timezone			varchar(40),
`modification date` date,
primary key (geonameId)
);

LOAD DATA LOCAL INFILE '/Users/Emil/examproject/gutenberg/dbSetup.csv' INTO TABLE cities
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;



