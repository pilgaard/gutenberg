drop database if exists gutenberg;

CREATE DATABASE gutenberg
  DEFAULT CHARACTER SET utf8mb4;

use gutenberg;

drop table if exists books;
create table books
(
id 					int NOT NULL AUTO_INCREMENT,
title 				varchar(500),
author				varchar(500),				
primary key (id)
);

drop table if exists CitiesInBooks;
create table CitiesInBooks
(
bookId				int,
cityId				int,
primary key (BookId, cityId)
);

drop table if exists cities;
create table cities
(
geonameId			int,
`name` 				varchar(200),
asciiname			varchar(200),
lat					Decimal(10,8),
`long`				Decimal(11,8),
`countryCode`		char(2),
population			bigint,
elevation			int,
timezone			varchar(40),
primary key (geonameId)
);

alter table CitiesInBooks
add foreign key (bookid) references books(id),
add foreign key (cityId) references cities(geonameId);

LOAD DATA LOCAL INFILE '/Users/Emil/examproject/gutenberg/DbSetup_cities.csv' INTO TABLE cities
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;

LOAD DATA LOCAL INFILE '/Users/Emil/examproject/gutenberg/DbSetup_books.csv' INTO TABLE books
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;

LOAD DATA LOCAL INFILE '/Users/Emil/examproject/gutenberg/DbSetup_CitiesInBooks.csv' INTO TABLE CitiesInBooks
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;

