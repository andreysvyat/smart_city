create table if not exists app_user (
    id uuid not null,
    first_name varchar(255),
    middle_name varchar(255),
    last_name varchar(255),
    login varchar(255),
    email text,
    type varchar(255),
    primary key (id)
);

create table if not exists okveds(
    id uuid primary key,
    division varchar(1) not null,
    clas int not null,
    subClas int not null,
    "group" int not null,
    subGroup int not null,
    type int not null
);

create table if not exists city_case_types(
    id uuid primary key,
    okved_id uuid not null references okveds(id),
    name varchar(255) not null,
    description text,
    code varchar(64) not null unique
);

create table if not exists city_case (
    id uuid not null,
    description varchar(255),
    location varchar(255) not null,
    author_id uuid references app_user(id) not null,
    inited_on timestamp with time zone not null,
    status varchar(64) not null,
    type varchar(64) not null references city_case_types(code),
    address text,
    primary key (id)
);

create table if not exists files (
    city_case_id uuid not null references city_case(id),
    file_link text
);

create table if not exists contractor (
	id uuid not null primary key,
	"area" text not null,
	full_name text not null,
	inn bigint,
	user_id uuid not null references app_user(id)
);

create table if not exists contractor_okveds(
    contractor_id uuid not null references contractor(id),
    okved_id uuid not null references okveds(id)
);