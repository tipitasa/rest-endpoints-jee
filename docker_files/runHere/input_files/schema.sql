-- schema.sql
-- Since we might run the import many times we'll drop if exists
DROP DATABASE IF EXISTS assignmentdb;

CREATE DATABASE assignmentdb;

-- change to assignmentdb database
\c assignmentdb;

-- create table in assignmentdb database
CREATE TABLE IF NOT EXISTS assignmentTable (
  id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  someDate DATE
);
