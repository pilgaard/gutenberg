-- Create syntax for PROCEDURE 'GetBooksByAuthorName'
DELIMITER ;;
CREATE PROCEDURE `GetBooksByAuthorName`(authorName varchar(500))
begin 
SELECT author, title, group_concat(c.geonameId) as cityID from books b
	join CitiesInBooks cib on cib.bookId = b.id
	join cities c on c.geonameId = cib.cityId
where b.author = authorName
group by b.id;
end;;
DELIMITER ;

-- Create syntax for PROCEDURE 'GetBooksByCity'
DELIMITER ;;
CREATE PROCEDURE `GetBooksByCity`(cityName varchar(50))
begin
select b.id, b.title, b.author
from books b
		join CitiesInBooks cib on cib.bookId = b.Id
		join cities c on c.geonameId = cib.cityId
		where c.asciiname = cityName;	
end;;
DELIMITER ;

-- Create syntax for PROCEDURE 'GetBooksByGeoLocation'
DELIMITER ;;
CREATE PROCEDURE `GetBooksByGeoLocation`(latitude Decimal(10,8), longitude Decimal(11,8))
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
  ORDER BY distance_in_km;
end;;
DELIMITER ;

-- Create syntax for PROCEDURE 'getCities'
DELIMITER ;;
CREATE PROCEDURE `getCities`()
begin
select geonameId, asciiname, lat, `long` from cities 
order by asciiname asc;
end;;
DELIMITER ;

-- Create syntax for PROCEDURE 'GetCitiesByBookTitle'
DELIMITER ;;
CREATE PROCEDURE `GetCitiesByBookTitle`(BookTitle varchar (500))
begin
select geonameId, asciiname, lat, `long` 
from cities c
join CitiesInBooks cib on cib.cityId = c.geonameId
join books b on b.id = cib.bookId
	where b.title = BookTitle;
end;;
DELIMITER ;