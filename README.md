# tqks-general-database
Generalized PostgreSQL database
## Configuration

The file /config/asr_schema.sql needs to have the database name set. It is referenced in to locations:<br/>
* Line 18: CREATE DATABASE
* Line 21: \c<br/><br/>
The database name chosen must then be set in the file /config/postgress-props.xml
