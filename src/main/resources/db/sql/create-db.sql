--DROP TABLE users IF EXISTS;

CREATE TABLE users (
  itemId         INTEGER PRIMARY KEY,
  name VARCHAR(30),
  email  VARCHAR(50)
);--DROP TABLE users IF EXISTS;

CREATE TABLE customers (
  itemId INTEGER PRIMARY KEY,
  name VARCHAR(30)
);

CREATE TABLE reviews (
  itemId INTEGER PRIMARY KEY,
  text VARCHAR(200),
  product_id INTEGER,
  customer_id INTEGER
);--DROP TABLE users IF EXISTS;

CREATE TABLE products (
    itemId INTEGER PRIMARY KEY,
    name VARCHAR(50),
    shortDescription VARCHAR(5000),
    categoryNode INTEGER,
    salePrice DOUBLE,
    discount DOUBLE ,
    is_best_seller BIT,
    is_new BIT,
    thumbnailImage VARCHAR(50),
    remaining_quantity INTEGER,
    spec_id INTEGER
);

CREATE TABLE product_specifications (
    itemId INTEGER PRIMARY KEY,
    author VARCHAR(50),
    series_title VARCHAR(50),
    publisher VARCHAR(50),
    book_format VARCHAR(50),
    original_languages VARCHAR(50),
    number_of_pages INTEGER,
    isbn_13 VARCHAR(50),
    publication_date TIMESTAMP ,
    dimensions VARCHAR(50),
    isbn_10 VARCHAR(50)

);

