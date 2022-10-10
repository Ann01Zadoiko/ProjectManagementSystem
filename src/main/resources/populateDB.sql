insert into companies (id_company,name_company) values (11,'Cisco','Australia');
insert into companies (id_company,name_company,country) values (12,'Sherlock','Japan');

insert into customers(id_coustomer,name_customer,country) values (21,'Mr. Park','South Korea');
insert into customers(id_coustomer,name_customer,country) values (22,'Mr. Backer','New Zealand');

insert into projects(id_project,name_project,id_company,id_customer) values (31,'Robot Sofia',11,21);
insert into projects(id_project,name_project,id_company,id_customer) values (32,'SpaceOf',11,22);
insert into projects(id_project,name_project,id_company,id_customer) values (33,'Cyberpunk',12,22);
insert into projects(id_project,name_project,id_company,id_customer) values (34,'Universe of Luidgy',12,21);

insert into developers(id_developer,name_developer,age) values (41,'Ms. Garcia',25);
insert into developers(id_developer,name_developer,age) values (42,'Mr. Williams',36);
insert into developers(id_developer,name_developer,age) values (43,'Mr. Walker',27);
insert into developers(id_developer,name_developer,age) values (44,'Ms. Brown',38);
insert into developers(id_developer,name_developer,age) values (45,'Ms. Harris',41);
insert into developers(id_developer,name_developer,age) values (46,'Mr. Gomez',43);

insert into skills(id_skillint,programming_language,skill_level) values (51,'Java','Senior');
insert into skills(id_skillint,programming_language,skill_level) values (52,'Java','Middle');
insert into skills(id_skillint,programming_language,skill_level) values (53,'C++','Junior');
insert into skills(id_skillint,programming_language,skill_level) values (54,'C++','Senior');
insert into skills(id_skillint,programming_language,skill_level) values (55,'JS','Middle');
insert into skills(id_skillint,programming_language,skill_level) values (56,'JS','Junior');

insert into developer_projects(id_developer,id_project) values (41,31);
insert into developer_projects(id_developer,id_project) values (41,33);
insert into developer_projects(id_developer,id_project) values (42,32);
insert into developer_projects(id_developer,id_project) values (42,34);
insert into developer_projects(id_developer,id_project) values (43,32);
insert into developer_projects(id_developer,id_project) values (43,33);
insert into developer_projects(id_developer,id_project) values (44,31);
insert into developer_projects(id_developer,id_project) values (44,34);
insert into developer_projects(id_developer,id_project) values (45,34);
insert into developer_projects(id_developer,id_project) values (45,33);
insert into developer_projects(id_developer,id_project) values (46,32);
insert into developer_projects(id_developer,id_project) values (46,31);

insert into developer_skills(id_developer,id_skill) values (41,51);
insert into developer_skills(id_developer,id_skill) values (41,53);
insert into developer_skills(id_developer,id_skill) values (42,52);
insert into developer_skills(id_developer,id_skill) values (42,56);
insert into developer_skills(id_developer,id_skill) values (43,55);
insert into developer_skills(id_developer,id_skill) values (43,54);
insert into developer_skills(id_developer,id_skill) values (44,51);
insert into developer_skills(id_developer,id_skill) values (44,56);
insert into developer_skills(id_developer,id_skill) values (45,52);
insert into developer_skills(id_developer,id_skill) values (45,53);
insert into developer_skills(id_developer,id_skill) values (46,52);
insert into developer_skills(id_developer,id_skill) values (46,55);