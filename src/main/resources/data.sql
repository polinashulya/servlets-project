

create table users(id serial, login character(20), password character(10), first_name character(10), second_name character(10), birth_date date, banned bool);


-- select id, first_name from users;
--
--
 --

insert into users(first_name) values ('test');
insert into users(login, password) values ('test', 'test');

insert into users(first_name) values ('test');


insert into users(login,first_name) values ('login','polya');

select * from users;


select id, first_name from users where id = 6;
UPDATE users  SET banned=false where id= 6;
-- comment

