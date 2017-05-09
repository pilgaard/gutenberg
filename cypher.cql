USING PERIODIC COMMIT
LOAD CSV with headers 
FROM 'https://raw.githubusercontent.com/pilgaard/gutenberg/master/dbSetup.csv' AS row
CREATE (:City { id: row.geonameId, name: row.name, asciiname: row.asciiname,
lat: row.lat, long: row.long, countryCode: row.countryCode, population: row.population,
elevation: row.elevation, timezone: row.timezone});