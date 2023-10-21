## SpringBoot_MongoDB_JavaAPP
Simple training Java app with Spring Boot, connected to MongoDB database

## Running base locally
You need to have docker.
In MongoDB folder, run:
docker-compose -f docker-compose.yaml

## Notes
### Command for building images:
build containers:
```
docker-compose -f docker-compose.yaml up
```
write all containers:
```
docker-ps
```
entering the container (long number is id):
```
docker exec -it 8803184cfb0e bash	 
```
log to mongo database:
```
mongosh mongodb://localhost:27017 -u rootuser -p rootpass
```
### Inside database:
show all databases:
```
show dbs; 
```
create database:
```
use database_name;
```
get name of Database:
```
db.getName()
```
add collection to DB:
```
db.createCollection(“hello”);
```
delete database:
```
db.dropDatabase();
```
list all available methods:
```
db.help();
```
### COLLECTIONS
Mongo stores documents (rows) in collections (table)

### DOCUMENTS
MongoDB stores data records as BSON documents. BSON is a binary representation of JSON documents.

### Simple operations
inserting objects:
```
db.<databasename>.insert(<object name>);
```
inserting created object (insertMany() for multiple documents):
```
db.kacperDB.insert.(student);	
```
print objects:
```
db.<databasename>.find();		
```

### Finding
select by name:
```
db.kacperDB.find({firstName: 'Kajto'}, {firstName: 1});	
```
select all with firstName and LastName fields:
```
db.kacperDB.find({},{firstName: 1, lastName:1});	
```
select all without firstName:
```
db.kacperDB.find({},{firstName: 0});	
```
update field firstName (deleteOne() for deleting documents):
```
db.kacperDB.updateOne({_id: ObjectId("64f6e7d3f4225afa5a983383")},{$set:{firstName: 'KajtoAlfa'}});	
```



