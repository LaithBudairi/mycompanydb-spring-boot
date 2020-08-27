create table role
(
    id   int auto_increment
        primary key,
    name varchar(20) not null,
    constraint name_UNIQUE
        unique (name)
);

create table user
(
    id       int auto_increment
        primary key,
    name     varchar(50) not null,
    email    varchar(50) not null,
    password varchar(50) not null
);

create table device
(
    id      int auto_increment
        primary key,
    user_id int         not null,
    name    varchar(50) not null,
    constraint fk_user_id
        foreign key (user_id) references user (id)
);

create index fk_user_id_idx
    on device (user_id);

create table location
(
    id        int auto_increment
        primary key,
    device_id int         null,
    name      varchar(50) null,
    longitude double      not null,
    Latitude  double      not null,
    date      datetime    null,
    constraint fk_device_id
        foreign key (device_id) references device (id)
);

create index fk_device_id_idx
    on location (device_id);

create table user_profile
(
    id         int auto_increment
        primary key,
    user_id    int         not null,
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    image      varchar(45) null,
    street1    varchar(20) null,
    city1      varchar(20) null,
    state1     varchar(20) null,
    zipcode1   varchar(20) null,
    country1   varchar(20) null,
    street2    varchar(20) null,
    city2      varchar(20) null,
    state2     varchar(20) null,
    zipcode2   varchar(20) null,
    country2   varchar(20) null,
    constraint user_id_UNIQUE
        unique (user_id),
    constraint fk_user_id_profile
        foreign key (user_id) references user (id)
);

create index fk_user_profile_user1_idx
    on user_profile (user_id);

create table user_role
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    constraint fk_user_has_role_role1
        foreign key (role_id) references role (id),
    constraint fk_user_has_role_user
        foreign key (user_id) references user (id)
);

create index fk_user_has_role_role1_idx
    on user_role (role_id);

create index fk_user_has_role_user_idx
    on user_role (user_id);

