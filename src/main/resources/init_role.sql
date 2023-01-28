INSERT IGNORE INTO role (`id`, `description`, `role_type`)
VALUES (1, 'USER', 'ROLE_USER'),
       (2, 'ADMIN', 'ROLE_ADMIN');

insert into user (`id`, `name`, `password`, `user_name`)
values (1, 'groom', 'groom', 'groom@groom.com');