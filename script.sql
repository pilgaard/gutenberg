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

LOAD DATA LOCAL INFILE '/Users/Emil/examproject/gutenberg/dbSetup.csv' INTO TABLE cities
fields terminated by ',' enclosed by '"'
lines terminated by '\n'
ignore 1 lines;

DELIMITER $$
create procedure getCities()
begin
select geonameId, asciiname, lat, `long` from cities 
order by asciiname asc;
end $$
DELIMITER ;
call getCities();

drop procedure if exists GetBooksByAuthorName;
DELIMITER ;;
CREATE PROCEDURE GetBooksByAuthorName(authorName varchar(500))
begin 
SELECT author, title, group_concat(c.geonameId) as cityID from books b
	join CitiesInBooks cib on cib.bookId = b.id
	join cities c on c.geonameId = cib.cityId
where b.author = authorName
group by b.id;
end;;
DELIMITER ;
call GetBooksByAuthorName("William Shakespeare");

DELIMITER ;;
CREATE PROCEDURE GetCitiesByBookTitle(BookTitle varchar (500))
begin
select geonameId, asciiname, lat, `long` 
from cities c
join CitiesInBooks cib on cib.cityId = c.geonameId
join books b on b.id = cib.bookId
	where b.title = BookTitle;
end;;
DELIMITER ;
call GetCitiesByBookTitle("Byron");

#Given a city name your application returns all book titles with corresponding authors that mention this city.
drop procedure if exists GetBooksByCity;
DELIMITER $$
create procedure GetBooksByCity(cityName varchar(50))
begin
select b.id, b.title, b.author
from books b
		join CitiesInBooks cib on cib.bookId = b.Id
		join cities c on c.geonameId = cib.cityId
		where c.asciiname = cityName;
 	
end $$
DELIMITER ;
call GetBooksByCity("London");


#Given a geolocation, your application lists all books mentioning a city in vicinity of the given geolocation.
drop procedure if exists GetBooksByGeoLocation;
DELIMITER $$
create procedure GetBooksByGeoLocation(latitude Decimal(10,8), longitude Decimal(11,8))
begin
SELECT b.*, z.asciiname, 
        p.distance_unit
                 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
                 * COS(RADIANS(z.lat))
                 * COS(RADIANS(p.longpoint) - RADIANS(z.long))
                 + SIN(RADIANS(p.latpoint))
                 * SIN(RADIANS(z.lat)))) AS distance_in_km
  FROM cities AS z
  JOIN CitiesInBooks cib on cib.cityId = z.geonameId
  JOIN books b on b.id = cib.bookId
  JOIN (   /* these are the query parameters */
        SELECT  latitude  AS latpoint,  longitude AS longpoint,
                50.0 AS radius,      111.045 AS distance_unit
    ) AS p ON 1=1
  
  WHERE z.lat
     BETWEEN p.latpoint  - (p.radius / p.distance_unit)
         AND p.latpoint  + (p.radius / p.distance_unit)
    AND z.long
     BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
         AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
  ORDER BY distance_in_km
  LIMIT 1;
end $$
DELIMITER ;
call GetBooksByGeoLocation(48.81680000, 9.57690000);


