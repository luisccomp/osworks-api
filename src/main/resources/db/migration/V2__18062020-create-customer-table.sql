create table customer (
	id bigint primary key auto_increment,
    name varchar(80) not null,
    email varchar(60) not null,
    telephone varchar(20) not null
);