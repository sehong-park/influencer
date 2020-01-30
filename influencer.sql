create table books (
    id serial primary key,
    title varchar(50),
    author varchar(50),
    image varchar(255)
);

insert into books (title, author, image)
values ('자바의정석', '남궁성', 'http://image.yes24.com/goods/24259565/L');

select * from books;

create table users (
    id serial primary key,
    email varchar(50) unique,
    password varchar(100),
    enabled boolean not null
);

insert into users (email, password, enabled)
values('admin@admin.com', '1234', true);

create table authorities (
    email varchar(50) not null,
    authority varchar(50) not null
);
insert into authorities (email, authority)
values('admin@admin.com', 'USER');

select * from users;

select * from authorities;

INSERT INTO authorities (email, authority)
VALUES ('admin@admin.com', 'ROLE_ADMIN');

INSERT INTO authorities (email, authority)
VALUES ('admin@admin.com', 'ROLE_USER');

delete from authorities where authority = 'USER';


create table reviews (
    id serial primary key,
    text text,
    book_id integer,
    user_id integer
);

select * from reviews;

alter table reviews
add column rating int not null default 5;

select * from books where title like '%클%';


insert into books (title, author, image)
values ('The Java', '남궁성', 'http://image.yes24.com/goods/24259565/L');

alter table books
add column price int not null default 12900;

create table carts (
  id serial primary key,
  status integer default 0,
  user_id integer references users not null
);

create table items (
  cart_id integer references carts not null,
  book_id integer references books not null,
  amount integer not nullw
);

select * from items;

select * from carts;
select * from carts where status = 0 and user_id = 1;
