CREATE TABLE `hmtf_db`.roles (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(255) NOT NULL,
    `user_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

ALTER TABLE `hmtf_db`.`roles`
ADD INDEX `fk_roles_user_idx` (`user_id` ASC);
ALTER TABLE `hmtf_db`.`roles`
ADD CONSTRAINT `fk_roles_user_idx`
  FOREIGN KEY (`user_id`)
  REFERENCES `hmtf_db`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;