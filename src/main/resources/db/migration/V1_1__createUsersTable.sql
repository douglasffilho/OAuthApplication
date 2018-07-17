CREATE TABLE `hmtf_db`.users (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password` VARCHAR(8000) NOT NULL,
    `phone` VARCHAR(17) NOT NULL,
    `account_expiration_date` DATETIME NOT NULL,
    `credentials_expiration_date` DATETIME NOT NULL,
    `is_account_non_expired` BIT(1) NOT NULL,
    `is_account_non_locked` BIT(1) NOT NULL,
    `is_credentials_non_expired` BIT(1) NOT NULL,
    `is_enabled` BIT(1) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC),
    UNIQUE INDEX `phone_UNIQUE` (`phone` ASC)
)
ENGINE = InnoDB;