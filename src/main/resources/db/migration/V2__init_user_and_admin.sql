insert into users(id, created_at, password, updated_at, username)
values
       (1,'2022-03-28T10:43:19.445590400Z','$2a$10$m1U.DFM.uNP73R3Vf6qzzOu7IrtPq2nL31SUE49MmDZVcRPvj61tO','2022-03-28T10:43:19.445590400Z','qwe'),
       (2,'2022-03-28T10:43:19.445590400Z','$2a$10$m1U.DFM.uNP73R3Vf6qzzOu7IrtPq2nL31SUE49MmDZVcRPvj61tO','2022-03-28T10:43:19.445590400Z','asd');
insert into user_roles(user_id, role)
VALUES
       (1,'ADMIN'),
       (2,'USER')