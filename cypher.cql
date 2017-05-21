USING PERIODIC COMMIT
LOAD CSV with headers
FROM 'https://raw.githubusercontent.com/pilgaard/gutenberg/master/DbSetup_cities.csv' AS row
CREATE (:City { id: toint(row.geonameId), name: row.name, asciiname: row.asciiname,
lat: todouble(row.lat), long: todouble(row.long), countryCode: row.countryCode, population: toint(row.population),
elevation: toint(row.elevation), timezone: row.timezone});

USING PERIODIC COMMIT
LOAD CSV with headers
FROM 'https://raw.githubusercontent.com/pilgaard/gutenberg/master/DbSetup_books.csv' AS row
CREATE (:Book {id: toint(row.id), title: row.title, author: row.author});

CREATE INDEX ON :City(id);
CREATE INDEX ON :Book(id);

USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM "https://raw.githubusercontent.com/pilgaard/gutenberg/master/DbSetup_CitiesInBooks.csv" AS edges
MATCH (a:City { id: toint(edges.cityId)})
MATCH (b:Book { id: toint(edges.bookId)})
CREATE (a)-[:MENTIONED_IN]->(b);
