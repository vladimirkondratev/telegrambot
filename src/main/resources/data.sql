DELETE
FROM cityinfo;
DELETE
FROM cities;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO CITIES (name)
VALUES ('moscow'),
       ('yakutsk');

INSERT INTO CITYINFO (city_id, info)
VALUES (100000, 'Red square'),
       (100001, 'Permafrost museum'),
       (100001, 'Lenin square');