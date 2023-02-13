create table categories
(
    id         bigserial primary key,
    title      varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (title)
values ('Комплектующие'),
       ('Периферия'),
       ('Разное');

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    category_id bigserial references categories (id),
    price       int,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, price, category_id)
values ('HDD', 2500, 1),
       ('SSD', 3500, 1),
       ('Ryzen3', 12000, 1),
       ('Ryzen5', 25000, 1),
       ('Ryzen7', 35000, 1),
       ('Ryzen9', 120000, 1),
       ('Flash Disk', 1500, 3),
       ('Flash microSD', 1000, 3),
       ('LCD монитор', 12000, 2),
       ('GeForce RTX2070', 75000, 1),
       ('Mouse', 350, 2),
       ('Keyboard', 1000, 2);

create table users
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    address    varchar(255),
    phone      varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id   bigserial primary key,
    name varchar(50) not null
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
        ('neel', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'kotmebius@gmail.com'),
        ('lisa', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'lisa-liska@rambler.ru');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 1);

create table orders
(
    id          bigserial primary key,
    user_id     bigint not null references users (id),
    total_price int    not null,
    address     varchar(255),
    phone       varchar(255), 
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

-- insert into orders (user_id, total_price, address, phone)
-- values (1, 200, 'address', '12345');
--
-- insert into order_items (product_id, order_id, quantity, price_per_product, price)
-- values (1, 1, 2, 100, 200);
