
DROP TABLE test if exists;
DROP TABLE pctask if exists;

CREATE TABLE test (
    id integer identity,
    name varchar(40),
    description varchar(256),
    create_date datetime
);

INSERT INTO test (name, description, create_date) VALUES (
    'lichhao', 'Description for lichhao', '2012-10-29 00:00:00');
    
INSERT INTO test (name, description, create_date) VALUES (
    'lichhao2', 'Description for lichhao2', '2012-10-29 15:22:50');

commit;
