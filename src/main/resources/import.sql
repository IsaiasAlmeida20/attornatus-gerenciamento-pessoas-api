insert into tb_person (name, birth_date) values ('Isaias', '1998-05-12');
insert into tb_person (name, birth_date) values ('Edirene', '1999-04-01');

insert into tb_addres (public_place, zip_code, number, city, status) values ('Rua A', 100000, 1, 'Mogi das Cruzes', 'MAIN');
insert into tb_addres (public_place, zip_code, number, city, status) values ('Rua B', 110000, 2, 'Itu', 'SECONDARY');

INSERT INTO tb_person_address (person_id, address_id) VALUES (1, 2);
INSERT INTO tb_person_address (person_id, address_id) VALUES (2, 2);
INSERT INTO tb_person_address (person_id, address_id) VALUES (2, 1);
