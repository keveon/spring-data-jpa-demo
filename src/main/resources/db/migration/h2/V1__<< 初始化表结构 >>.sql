create sequence t_dept_seq start with 1 increment by 50;
create sequence t_employee_seq start with 1 increment by 50;
create table t_dept
(
    id   integer not null,
    name varchar(255),
    primary key (id)
);
create table t_employee
(
    id              integer not null,
    entry_date      date,
    gender          integer,
    id_card         varchar(18),
    last_login_ip   varchar(255),
    last_login_time timestamp(6),
    name            varchar(20),
    phone           varchar(11),
    status          integer,
    turnover_date   date,
    dept_id         integer,
    primary key (id),
    foreign key (dept_id) references t_dept,
    unique (phone),
    unique (id_card)
);
create index IDX_t_employee_phone on t_employee (phone);
create index IDX_t_employee_id_card on t_employee (id_card);
