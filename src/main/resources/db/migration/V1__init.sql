create table products
(
    id bigserial primary key,
    title varchar (255),
    price int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into products (title, price) values
('HDD', 2500),
('SSD', 3500),
('Ryzen3', 12000),
('Ryzen5', 25000),
('Ryzen7', 35000),
('Ryzen9', 120000),
('Flash Disk', 1500),
('Flash microSD', 1000),
('LCD монитор', 12000),
('GeForce RTX2070', 75000),
('Mouse', 350),
('Keyboard', 1000);

create table users (
                       id         bigserial primary key,
                       username   varchar(36) not null,
                       password   varchar(80) not null,
                       email      varchar(50) unique
);

create table roles (
                       id         bigserial primary key,
                       name       varchar(50) not null
);

CREATE TABLE users_roles (
                             user_id bigint not null references users (id),
                             role_id bigint not null references roles (id),
                             primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
       ('neel', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'kotmebius@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);
