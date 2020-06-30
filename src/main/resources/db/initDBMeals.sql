DROP TABLE IF EXISTS meals;

CREATE TABLE meals
(
    id          SERIAL PRIMARY KEY     NOT NULL,
    dateTime    TIMESTAMP DEFAULT now() NOT NULL,
    description VARCHAR                 NOT NULL,
    calories    INTEGER                 NOT NULL,
    userID      INTEGER references users (id) default '100001'
);
CREATE UNIQUE INDEX meals_unique_user_id_dateTime ON meals (userID, dateTime);
