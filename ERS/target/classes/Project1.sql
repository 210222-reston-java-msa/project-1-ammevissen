--https://www.cybertec-postgresql.com/en/mapping-oracle-datatypes-to-postgresql/

drop table if exists ers_users_roles cascade;
drop table if exists ers_reimbursement_status cascade;
drop table if exists ers_reimbursement_type cascade;
drop table if exists ers_users cascade;
drop table if exists ers_reimbursement cascade;


drop table if exists ers_users_roles cascade;
CREATE table ers_users_roles(
ers_user_role_id integer primary key,
user_role varchar(10)
);

drop table if exists ers_reimbursement_status cascade;
CREATE table ers_reimbursement_status(
reimb_status_id integer primary key,
reimb_status varchar (10)
);

drop table if exists ers_reimbursement_type cascade;
CREATE table ers_reimbursement_type(
reimb_type_id integer primary key,
reimb_type varchar(10)
);

drop table if exists ers_users cascade;
CREATE table ers_users(
ers_users_id serial primary key,
ers_username varchar (50) unique,
ers_password varchar (50),
ers_first_name varchar (100),
ers_last_name varchar (100),
ers_email varchar (150) unique,
ers_role_id integer,
constraint users_roles_fk foreign key (ers_role_id) references ers_users_roles(ers_user_role_id)
);

drop table if exists ers_reimbursement cascade;
CREATE table ers_reimbursement(
reimb_id serial primary key,
reimb_amount double precision not null default 0 check (reimb_amount>=0),
reimb_submitted date default current_date,
reimb_resolved date,
reimb_description varchar(250),
reimb_receipt bytea,
reimb_author integer,
reimb_resolver integer,
reimb_status_id integer,
reimb_type_id integer,
constraint ers_users_fk_auth foreign key (reimb_author) references ers_users(ers_users_id),
constraint ers_users_fk_reslver foreign key (reimb_resolver) references ers_users(ers_users_id),
constraint ers_reimbursement_status_fk foreign key (reimb_status_id) references ers_reimbursement_status(reimb_status_id),
constraint ers_reimbursement_type_fk foreign key (reimb_type_id) references ers_reimbursement_type(reimb_type_id)
);


INSERT into  ers_users_roles(ers_user_role_id, user_role)
values (1, 'employee'),
(2, 'manager');


INSERT INTO ers_users(ers_username, ers_password, ers_first_name, ers_last_name, ers_email, ers_role_id)
values
('ammevissen', 'p@55w0rd', 'andy', 'mevissen', 'ammevissen@email', 1),
('sgavrila', 'p@55w0rd', 'sophia', 'gavrila', 'sgavrila@email', 2);

insert into ers_reimbursement_status(reimb_status_id, reimb_status)
values(1, 'pending'),
(2, 'approved'),
(3, 'rejected');

insert into ers_reimbursement_type(reimb_type_id, reimb_type)
values(1, 'tavel'),
(2, 'education'),
(3, 'computer'),
(4, 'other');

INSERT into ers_reimbursement(reimb_amount, reimb_description, reimb_author, reimb_status_id, reimb_type_id)
values(3.14, 'pi day food', 5, 1, 4);


SELECT *
from ers_users_roles;

SELECT *
from ers_reimbursement_status;

SELECT *
from ers_reimbursement_type;

SELECT *
from ers_users;

SELECT *
from ers_reimbursement;
