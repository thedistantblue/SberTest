create table abonent(
    id int not null auto_increment primary key,
    surname varchar(30) not null,
    firstname varchar(30) not null,
    secondname varchar(30),
    birth date not null,
    tariff int not null,
    minutes int not null
);

create table tariff(
    id int not null auto_increment primary key,
    description varchar(50)
);

alter table abonent add constraint fk_constraint foreign key (tariff) references tariff(id)