insert into TARIFF(description)
values ('description1'),
       ('description2'),
       ('description3');

insert into ABONENT(surname, firstname, secondname, birth, tariff, minutes)
values ('Ivanov', 'Ivan', 'Ivanovich', '1960-12-25', 1, 12345),
       ('Petrov', 'Petr', 'Petrovich', '1950-06-20', 2, 23456),
       ('Sidorov', 'Sidor', 'Sidorovich', '1940-01-15', 3, 34567)