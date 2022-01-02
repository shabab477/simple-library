INSERT INTO book(copy_count, created_at) VALUES (10, now());
SET @book_id = LAST_INSERT_ID();
INSERT INTO book_meta(name, isbn, published_date, book_id) VALUES('Clean Code', '123', now(), @book_id);
SET @book_meta_id = LAST_INSERT_ID();
INSERT INTO book_author(author_name, book_id) VALUES('Robert C. Martin', @book_meta_id);

INSERT INTO book(copy_count, created_at) VALUES (1, now());
SET @book_id = LAST_INSERT_ID();
INSERT INTO book_meta(name, isbn, published_date, book_id) VALUES('Atomic Habits', '234', now(), @book_id);
SET @book_meta_id = LAST_INSERT_ID();
INSERT INTO book_author(author_name, book_id) VALUES('Clear James', @book_meta_id);

INSERT INTO book(copy_count, created_at) VALUES (10, now());
SET @book_id = LAST_INSERT_ID();
INSERT INTO book_meta(name, isbn, published_date, book_id) VALUES('The Art of War', '345', now(), @book_id);
SET @book_meta_id = LAST_INSERT_ID();
INSERT INTO book_author(author_name, book_id) VALUES('Sun Tzu', @book_meta_id);

INSERT INTO book(copy_count, created_at) VALUES (10, now());
SET @book_id = LAST_INSERT_ID();
INSERT INTO book_meta(name, isbn, published_date, book_id) VALUES('Born a Crime', '456', now(), @book_id);
SET @book_meta_id = LAST_INSERT_ID();
INSERT INTO book_author(author_name, book_id) VALUES('Trevor Noah', @book_meta_id);

INSERT INTO book(copy_count, created_at) VALUES (10, now());
SET @book_id = LAST_INSERT_ID();
INSERT INTO book_meta(name, isbn, published_date, book_id) VALUES('Why Nations Fail', '567', now(), @book_id);
SET @book_meta_id = LAST_INSERT_ID();
INSERT INTO book_author(author_name, book_id) VALUES('Daron Robinson', @book_meta_id);
INSERT INTO book_author(author_name, book_id) VALUES('James A.', @book_meta_id);

INSERT INTO book(copy_count, created_at) VALUES (10, now());
SET @book_id = LAST_INSERT_ID();
INSERT INTO book_meta(name, isbn, published_date, book_id) VALUES('JUnit in Action', '789', now(), @book_id);
SET @book_meta_id = LAST_INSERT_ID();
INSERT INTO book_author(author_name, book_id) VALUES('Catalin Tudose', @book_meta_id);

INSERT INTO book(copy_count, created_at) VALUES (10, now());
SET @book_id = LAST_INSERT_ID();
INSERT INTO book_meta(name, isbn, published_date, book_id) VALUES('Outliers', '890', now(), @book_id);
SET @book_meta_id = LAST_INSERT_ID();
INSERT INTO book_author(author_name, book_id) VALUES('Malcom Gladwell', @book_meta_id);
