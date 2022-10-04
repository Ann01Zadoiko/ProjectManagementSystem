create table developers
(
id_developer int PRIMARY key,
	name_developer VARCHAR(25),
	age int
);

create table skills
(
    id_skill int primary key,
	programming_language VARCHAR(10),
	skill_level VARCHAR(10)
);

create table companies
(
    id_company int primary key,
	name_company VARCHAR(25),
	country VARCHAR(25)
);


create table customers
(
    id_customer int PRIMARY key,
	name_customer VARCHAR(25),
	country VARCHAR(25)
);


create table projects
(
    id_project int primary key,
	name_project VARCHAR(25),
	id_company int,
	id_customer int,
	FOREIGN key (id_company) REFERENCES companies,
	foreign key (id_customer) references customers
);


create table developer_projects
(
    id_project int,
	id_developer int,
	FOREIGN key (id_project) REFERENCES projects,
	foreign key (id_developer) references developers
);


create table developer_skills
(
    id_skill int,
	id_developer int,
	FOREIGN key (id_skill) REFERENCES skills,
	foreign key (id_developer) references developers
);



