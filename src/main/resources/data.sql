
insert into users(first_name) values ('test');
insert into users(login, password) values ('test', 'test');

insert into users(first_name) values ('test');


insert into users(login,first_name) values ('login','polya');

insert into users(login,first_name,second_name,birth_date) values ('login','polya','busya','1999-02-08');

select * from users where id = 2;

select * from users;


select id, first_name from users where id = 6;
UPDATE users  SET banned=false where id= 6;
-- comment

