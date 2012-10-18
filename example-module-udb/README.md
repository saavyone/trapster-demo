
Required:
- maven2
- local instance of mysql 5.1 or higher
- knowledge of MyBatis ORM framework using xml maps


1. Locate the "udb-ddl.sql" script in src/main/resources create a "udb" table in a local instance of mysql.
2. Add a column to "udb" table named "OAuthDate" that can support date/time.
3. Add an attribute to the "udb" class that will correspond to the new column added.
4. Locate UdbMapper.xml in the project, map the new "OAuthDate" field to the table column.
5. Anywhere within the Integration tests carried out in JUnit, demonstrate that OAuthDate is readable from the database.