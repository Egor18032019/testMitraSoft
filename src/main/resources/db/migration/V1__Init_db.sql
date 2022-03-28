-- alter table if exists user_roles
-- drop constraint if exists FKhfh9dx7w3ubf1co1vdev94g3f
-- drop table if exists user_roles cascade
-- drop table if exists users cascade
-- drop sequence if exists hibernate_sequence

create sequence hibernate_sequence start 1 increment 1;



create table users
(
    id         int8 not null,
    created_at timestamp,
    password   varchar(255),
    updated_at timestamp,
    username   varchar(255),
    primary key (id)
);

create table user_roles
(
    user_id int8         not null,
    role    varchar(255) not null
);


alter table if exists users
    add constraint user_username unique (username);

alter table if exists user_roles
    add constraint uk_user_roles unique (user_id, role);

alter table if exists user_roles
    add constraint user_roles_uk foreign key (user_id)
        references users on delete cascade