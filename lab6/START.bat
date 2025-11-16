psql -U postgres
ALTER USER postgres WITH PASSWORD 'abcd';
CREATE DATABASE bookdb;
\q
psql -U postgres -d bookdb -f init.sql

mvn "exec:java" "-Dexec.mainClass=com.example.lab6.lab6Application"

pause