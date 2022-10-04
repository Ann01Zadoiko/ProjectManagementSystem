alter table developers
add column salary int;

update public.developers set salary = 4000 where id_developer= 41;
update public.developers set salary = 3000 where id_developer = 42;
update public.developers set salary = 7000 where id_developer = 43;
update public.developers set salary = 9000 where id_developer = 44;
update public.developers set salary = 5000 where id_developer = 45;
update public.developers set salary = 4000 where id_developer = 46;

ALTER TABLE public.projects
ADD cost int;

update public.projects set cost = 50000 where id_project = 31;
update public.projects set cost = 40000 where id_project = 32;
update public.projects set cost= 60000 where id_project = 33;
update public.projects set cost = 35000 where id_project = 34;

alter table projects
add date_create date;

update public.projects set date_create = '2012-06-16' where id_project = 31;
update public.projects set date_create = '2020-12-01' where id_project = 32;
update public.projects set date_create = '2019-09-20' where id_project = 33;
update public.projects set date_create = '2019-04-23' where id_project = 34;
