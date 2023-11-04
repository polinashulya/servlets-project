
insert into users(firstname) values ('test');
insert into users(login, password) values ('test', 'test');

insert into users(firstname) values ('test');


insert into users(login,firstname) values ('login','polya');

insert into users(login,firstname,surname,birth_date) values ('login','polya','busya','1999-02-08');

select * from users where id = 2;

select * from users;

SELECT u.id,u.login,u.firstname,u.surname,u.birth_date,u.banned FROM users u ORDER BY id ASC;


select id, firstname from users where id = 6;
UPDATE users  SET banned=false where id= 6;
-- comment

select * from users where firstname like 'P%' order by id DESC ;