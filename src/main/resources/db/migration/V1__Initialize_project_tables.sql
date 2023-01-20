CREATE TABLE member
(
    id                 BIGINT NOT NULL AUTO_INCREMENT,
    name               VARCHAR(255) NOT NULL,
    password           VARCHAR(255) NOT NULL,
    created_at         TIMESTAMP NOT NULL,
    modified_at        TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE delivery
(
    id                 BIGINT NOT NULL AUTO_INCREMENT,
    member_id          BIGINT NOT NULL,
    destination        VARCHAR(255) NOT NULL,
    status             VARCHAR(255) NOT NULL,
    created_at         TIMESTAMP NOT NULL,
    modified_at        TIMESTAMP,
    PRIMARY KEY (id)
);

ALTER TABLE delivery
    ADD CONSTRAINT fk_delivery_member
        FOREIGN KEY (member_id) REFERENCES member (id);
