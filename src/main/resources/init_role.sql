INSERT IGNORE INTO role (`id`, `description`, `role_type`)
VALUES (1, 'USER', 'ROLE_USER'),
       (2, 'ADMIN', 'ROLE_ADMIN');

GRANT ALL PRIVILEGES ON *.* TO sparcs@'%' IDENTIFIED BY 'password' WITH GRANT OPTION;