INSERT INTO member(id, name, email, password, created_at, modified_at)
VALUES (-1, 'cwpark', 'cwpark@mail.com', '$2a$10$AWdoHdK8S1RmE5I8dU358Ort6aqtZO7Aee9wm9nKCb3mLGE7BaWlG', -- my-password
        '2023-01-01', null);

INSERT INTO delivery(id, member_id,destination,status,created_at,modified_at)
VALUES (-1, -1, '서울특별시 마포구 성암로 189', 'PREPARING', '2023-01-01', null);
