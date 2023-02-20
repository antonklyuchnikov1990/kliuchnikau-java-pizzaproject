insert into cafe (id, name, address) values ('20ebdfe3-191e-4f46-951a-b34b55611a4a', 'Tempo_Berlin', 'Berlin');
insert into cafe (id, name, address) values ('a010e9d0-f4fe-40c7-871d-72f4979a85ca', 'Tempo_Milan', 'Milan');
insert into cafe (id, name, address) values ('90e4801a-8d77-4b59-9189-787d329667ba', 'Tempo_Paris', 'Paris');

insert into pizza (id, name, size, price, ingredients, picture, cafe_id) values ('bea94a4b-0d87-4ffc-b8c7-1e14ae14e440', '4_seasons', 'small', 18.7, 'mashed tomato sauce, Mozzarella, ham, mushrooms, shrimp, mussels, pizza seasoning, garlic oil', '4_seasons.jpg', '20ebdfe3-191e-4f46-951a-b34b55611a4a');
insert into pizza (id, name, size, price, ingredients, picture, cafe_id) values ('bea94a4b-0d87-4ffc-b8c7-1e14ae14e441', 'margherita', 'small', 16.2, 'mashed tomato sauce, Mozzarella, Mozzarella in brine, pizza seasoning, garlic oil', 'margherita.jpg', 'a010e9d0-f4fe-40c7-871d-72f4979a85ca');
insert into pizza (id, name, size, price, ingredients, picture, cafe_id) values ('bea94a4b-0d87-4ffc-b8c7-1e14ae14e442', 'roman', 'small', 19.4, 'pureed tomato sauce, mozzarella, chicken, bacon, cheese sauce, pickled cucumbers, pizza seasoning, garlic butter', 'roman.jpg', '90e4801a-8d77-4b59-9189-787d329667ba');

insert into roles (id, name) values ('dc745da1-6477-447d-aec8-16bed770f9eb', 'ADMIN');
insert into roles (id, name) values ('8c8a1e8a-29da-4f42-b59f-bc27f35aaaf4', 'USER');

insert into users (id, email, password) values ('2f44675f-6fe7-4db4-833e-32733016da1f', 'tj@vv.de', '123');
insert into users (id, email, password) values ('2a994eaa-9339-4d70-9d60-7984884decbc', 're@vv.de', '1234');

insert into users_roles (user_id, roles_id) values ('2f44675f-6fe7-4db4-833e-32733016da1f', 'dc745da1-6477-447d-aec8-16bed770f9eb');
insert into users_roles (user_id, roles_id) values ('2a994eaa-9339-4d70-9d60-7984884decbc', '8c8a1e8a-29da-4f42-b59f-bc27f35aaaf4');

