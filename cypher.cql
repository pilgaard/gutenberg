USING PERIODIC COMMIT
LOAD CSV with headers
FROM 'https://raw.githubusercontent.com/pilgaard/gutenberg/master/DbSetup_cities.csv' AS row
CREATE (:City { id: row.geonameId, name: row.name, asciiname: row.asciiname,
lat: row.lat, long: row.long, countryCode: row.countryCode, population: row.population,
elevation: row.elevation, timezone: row.timezone});

USING PERIODIC COMMIT
LOAD CSV with headers
FROM 'https://raw.githubusercontent.com/pilgaard/gutenberg/master/DbSetup_books.csv' AS row
CREATE (:Book {id: row.id, title: row.title, author: row.author});

CREATE INDEX ON :City(id);
CREATE INDEX ON :Book(id);

USING PERIODIC COMMIT 
LOAD CSV WITH HEADERS FROM "https://raw.githubusercontent.com/pilgaard/gutenberg/master/DbSetup_CitiesInBooks.csv" AS row                   
MATCH (c:City {id: row.cityId}), (b:Book {id: (row.bookId)})                            
CREATE (c)-[:Mentioned_in]->(b);

