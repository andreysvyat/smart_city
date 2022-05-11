CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

insert into app_user (id, first_name, middle_name, last_name, login, email, "type")
values 
(uuid_generate_v4(), 'Mike', 'Nicklson', 'Denver', 'm_dvr', 'denver@gmail.com', 'CITIZEN'),
(uuid_generate_v4(), 'Alexey', 'Rudskoy', 'Maximovich', 'alex_rude', 'rudelex@ya.ru', 'CITIZEN'),
(uuid_generate_v4(), 'Alick', 'Borkov', 'Ibragimovich', 'alick_power', 'alick.borkov@mail.ru', 'CITIZEN'),
(uuid_generate_v4(), 'Il''yas', 'Hayretginov', 'Halitovich', 'halitovich', 'IHHayretdinov@gmail.com', 'CITIZEN'),
(uuid_generate_v4(), 'Ruslan', 'Alexandrovich', 'Beliy', 'standup_ruslan', 'ruslan.tnt@gpm.ru', 'CITIZEN');

insert into app_user (id, first_name, middle_name, last_name, login, email, "type")
values
(uuid_generate_v4(), 'Ibragim', 'Muhametdinov', 'Surenovich', 'ISMuhametdinov', 'ISMuhametdinov@mo.krasnodar.ru', 'OPERARTOR'),
(uuid_generate_v4(), 'Galina', 'Ivanova', 'Olegovna', 'GOIvanova', 'GOIvanova@mo.krasnodar.ru', 'OPERARTOR'),
(uuid_generate_v4(), 'Anastasiya', 'Lubimova', 'Valentinovna', 'AVLubimova', 'AVLubimova@mo.krasnodar.ru', 'OPERARTOR');

insert into app_user (id, first_name, middle_name, last_name, login, email, "type")
values
(uuid_generate_v4(), 'Евгений', 'Гундоров', 'Николаевич', 'gundurov', 'city-crystal@mail.ru', 'CONTRACTOR'),
(uuid_generate_v4(), 'Сергей', 'Батурин', 'Альбертович', 'baturin', 'slavyanskiydom777@mail.ru', 'CONTRACTOR');

insert into contractor (id, "area", full_name, inn, user_id)
values
(uuid_generate_v4(), 'Анапа', 'ООО "Сити Кристалл"', 2301072436, (select id from app_user where email = 'city-crystal@mail.ru')),
(uuid_generate_v4(), 'Анапа', 'ООО "Славянский дом"', 2301086703, (select id from app_user where email = 'slavyanskiydom777@mail.ru'));

insert into okveds (id, division, clas, subclas, "group", subGroup, type)
values
(uuid_generate_v4(), 'N', 81, 2, 9,  9, -1),
(uuid_generate_v4(), 'N', 81, 2, 9,  2, -1),
(uuid_generate_v4(), 'N', 81, 2, 2, -1, -1),
(uuid_generate_v4(), 'M', 71, 1, 1,  1, -1),
(uuid_generate_v4(), 'M', 71, 1, 1,  2, -1),
(uuid_generate_v4(), 'M', 71, 1, 1,  3, -1);

insert into contractor_okveds (contractor_id, okved_id)
values
((select id from contractor where inn = 2301072436),
 (select id from okveds where clas = 81 and subclas = 2 and "group" = 9 and subGroup = 9)),
((select id from contractor where inn = 2301086703),
 (select id from okveds where clas = 71 and subclas = 1 and "group" = 1 and subGroup = 1));