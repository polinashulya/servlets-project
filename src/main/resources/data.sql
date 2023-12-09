create table users
(
    id         serial,
    login      character(20) NOT NULL UNIQUE,
    password   character(20) NOT NULL,
    firstname  character(20) NOT NULL,
    surname    character(20) NOT NULL,
    country_id int,
    birth_date date,
    banned     bool,
    deleted    bool
);

create table countries
(
    id   serial,
    name character(20) NOT NULL UNIQUE
);

insert into users(login, password, firstname, surname, country_id, birth_date, banned, deleted)
values ('user1', 'pass12', 'Anton', 'Petrov', 2, 2001-03-08, false, false);
insert into users(login, password, firstname, surname, country_id, birth_date, banned, deleted)
values ('user2', 'pass123', 'Mike', 'Stern', 3, 2000-03-10, false, false);

insert into countries(name) values ('Spain');
insert into countries(name) values ('Belarus');
insert into countries(name) values ('Switzerland');
insert into countries(name) values ('Italy');


ALTER TABLE users
    ADD FOREIGN KEY (country_id) REFERENCES countries (id);