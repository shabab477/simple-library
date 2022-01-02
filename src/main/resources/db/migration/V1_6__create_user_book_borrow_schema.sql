
CREATE TABLE app_users_books (
     user_id integer,
     book_id integer,

     CONSTRAINT pk_user_book_id PRIMARY KEY (user_id, book_id),
     CONSTRAINT fk_app_user_borrow FOREIGN KEY (user_id) REFERENCES app_user(id),
     CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES book(id)
);