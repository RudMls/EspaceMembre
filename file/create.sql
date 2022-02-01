DROP TABLE IF EXISTS user;

-- -----------------------------------------------------------------------------
--       TABLE : USER
-- -----------------------------------------------------------------------------

CREATE TABLE user (

    id INT AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY(id)

);