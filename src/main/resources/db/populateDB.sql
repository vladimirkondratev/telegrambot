DELETE
FROM cityinfo;
DELETE
FROM city;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO CITY (name)
VALUES ('Moscow'),
       ('Yakutsk');

INSERT INTO CITYINFO (city_id, info)
VALUES (100000, 'Red square'),
       (100001, 'Permafrost museum');