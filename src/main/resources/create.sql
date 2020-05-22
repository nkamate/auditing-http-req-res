//drop table Audit;

create table Audit
(
    ID      uuid,
    payload json,
    request_uri text,
    method  varchar(20),
    status  text
);