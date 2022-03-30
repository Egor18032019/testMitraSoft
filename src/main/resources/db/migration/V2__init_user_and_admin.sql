insert into users(id, created_at, password, updated_at, username)
values
       (1,'2022-03-29T05:18:35.955806Z','$2a$10$m1U.DFM.uNP73R3Vf6qzzOu7IrtPq2nL31SUE49MmDZVcRPvj61tO','2022-03-29T05:18:35.955806Z','qwe'),
       (2,'2022-03-30T07:04:10.710532Z','$2a$10$m1U.DFM.uNP73R3Vf6qzzOu7IrtPq2nL31SUE49MmDZVcRPvj61tO','2022-03-30T07:04:10.710532Z','asd');
insert into user_roles(user_id, role)
VALUES
       (1,'ADMIN'),
       (2,'USER')