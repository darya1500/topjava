DROP TABLE IF EXISTS meals;
DROP SEQUENCE IF EXISTS meal_seq;

CREATE SEQUENCE meal_seq START WITH 1000;

CREATE TABLE meals
(
    id          INTEGER PRIMARY KEY           DEFAULT nextval('meal_seq'),
    dateTime    TIMESTAMP                     DEFAULT now() NOT NULL,
    description VARCHAR                                     NOT NULL,
    calories    INTEGER                                     NOT NULL,
    userID      INTEGER references users (id) default '100001'
);
CREATE UNIQUE INDEX meals_unique_user_id_dateTime ON meals (userID, dateTime);
