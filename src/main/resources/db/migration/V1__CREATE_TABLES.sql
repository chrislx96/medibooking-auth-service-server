create table authorities (
    authority_id serial primary key,
    permission varchar(30)
);

create table accounts (
    account_id serial primary key,
    username varchar(30),
    encoded_password varchar(100)
);

create table accounts_authorities (
    account_id int references accounts (account_id),
	authority_id int references authorities (authority_id),
	primary key (account_id, authority_id)
);
