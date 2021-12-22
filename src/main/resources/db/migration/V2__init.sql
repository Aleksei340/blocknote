CREATE TABLE groups
(
    id          VARCHAR(255) PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(10000) NOT NULL,
    user_id     VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE
);

CREATE TABLE user_group
(
    user_id     VARCHAR(255) NOT NULL,
    group_id    VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, group_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (group_id) REFERENCES groups (id)
);

CREATE TABLE groupNotes
(
    id          VARCHAR(255) PRIMARY KEY,
    title       VARCHAR(200) NOT NULL,
    content     VARCHAR(10000) NOT NULL,
    group_id    VARCHAR(255) NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groups (id)
        ON DELETE CASCADE
);