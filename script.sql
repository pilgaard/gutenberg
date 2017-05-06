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
geonameid		int,
`name` 			varchar(200),
asciiname		varchar(200),
alternatenames	varchar(10000),
lat				Decimal(10,8),
`long`			Decimal(11,8),
`feature class`	char(1),
`feature code`	varchar(10),
`country code`		char(2),
cc2				varchar(200),
`admin1 code`	varchar(20),
`admin2 code`	varchar(80), 
`admin3 code`	varchar(20),
`admin4 code`	varchar(20),			
population		bigint,
elevation		int,
dem				int,
timezone		varchar(40),
`modification date` date,
primary key (name)
);

drop table if exists continentCodes;
create table continentCodes
(
geonameId	int,
`code`		varchar(50),
primary key (geonameId)
);

INSERT INTO `continentCodes` (`geonameId`, `code`, `continent`)
VALUES
	(6255146, 'Africa', 'AF'),
	(6255147, 'Asia', 'AS'),
	(6255148, 'Europe', 'EU'),
	(6255149, 'North America', 'NA'),
	(6255150, 'South America', 'SA'),
	(6255151, 'Oceania', 'OC'),
	(6255152, 'Antarctica', 'AN');
	
LOAD DATA LOCAL INFILE '/Users/Emil/Dropbox/CPHBusiness/examproject/gutenberg/cities5000.txt' INTO TABLE cities;

