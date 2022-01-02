CREATE TABLE book (
    id integer primary key auto_increment,
    copy_count integer not null default 0,
    created_at datetime not null,
    updated_at datetime
);

CREATE TABLE book_meta (
   id integer primary key auto_increment,
   published_date date not null,
   name varchar(255) not null,
   isbn varchar(255) not null,
   book_id integer not null,

   CONSTRAINT fk_meta_book FOREIGN KEY (book_id) REFERENCES book(id)
);

CREATE TABLE book_author (
    author_name varchar(255) not null,
    book_id integer not null,

    CONSTRAINT fk_author_book FOREIGN KEY (book_id) REFERENCES book_meta (id)
);
