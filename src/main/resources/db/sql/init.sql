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

create table if not exists city_case (
    id uuid not null,
    description varchar(255),
    location varchar(255),
    author_id uuid references app_user(id) not null,
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
	inn int
)