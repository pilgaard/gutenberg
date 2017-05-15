drop database if exists gutenberg;

CREATE DATABASE gutenberg
  DEFAULT CHARACTER SET utf8mb4;

use gutenberg;

drop table if exists books;
create table books
(
id 					int NOT NULL AUTO_INCREMENT,
title 				varchar(100),
author				varchar(50),				
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

LOAD DATA LOCAL INFILE '/Users/Emil/examproject/gutenberg/dbSetup.csv' INTO TABLE cities
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;




/* 
#Given a city name your application returns all book titles with corresponding authors that mention this city.
DELIMITER $$
create procedure GetBooksByCity(cityName varchar(50))
begin
select titles, author from book
join CitiesInBooks on bookId = books.`id`
join cities on cityId = `CitiesInBooks`.`cityId`
where CitiesInBooks.bookId = bookID;
end $$
DELIMITER ;
*/

DELIMITER $$
create procedure getCities()
begin
select geonameId, asciiname, lat, `long` from cities 
order by asciiname asc;
end $$
DELIMITER ;

#call getCities();

/*
DELIMITER $$
create procedure getCitiesFromBook(bookID int)
begin
select `name`,  from cities
join CitiesInBooks on cityId = cities.geonameId
where CitiesInBooks.bookId = bookID;
end $$
DELIMITER ;
*/
#call getCitiesFromBook(1);