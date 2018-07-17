INSERT INTO `hmtf_db`.`users` (
    `id`,
    `username`,
    `email`,
    `password`,
    `phone`,
    `account_expiration_date`,
    `credentials_expiration_date`,
    `is_account_non_expired`,
    `is_account_non_locked`,
    `is_credentials_non_expired`,
    `is_enabled`
)

VALUES(
    null,
    'Administrador',
    'douglasf.filho@gmail.com',
    'admin',
    '16988487554',
    '1990-01-01 00:00:01',
    '1990-01-01 00:00:01',
    1,
    1,
    1,
    1
);