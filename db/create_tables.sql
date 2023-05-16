CREATE TABLE users (
	id varchar(255) NOT NULL,
	avatar_url varchar(255) NULL,
	display_name varchar(255) NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	"location" varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);