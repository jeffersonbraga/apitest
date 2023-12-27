create table movie_model (movie_id integer not null, movie_name varchar(255), movie_year integer, winner boolean, primary key (movie_id));
create table movie_model_movie_studio (movie_model_movie_id integer not null, movie_studio_studio_id integer not null);
create table movie_producer_model (producer_id integer not null, movie_id integer not null);
create table producer_model (producer_id integer not null, producer_name varchar(255), primary key (producer_id));
create table studio_model (studio_id integer not null, studio_name varchar(255), primary key (studio_id));
create sequence movie_model_seq start with 1 increment by 50;
create sequence producer_model_seq start with 1 increment by 50;
create sequence studio_model_seq start with 1 increment by 50;
alter table if exists movie_model_movie_studio add constraint fk_movie_studio_studio_id foreign key (movie_studio_studio_id) references studio_model;
alter table if exists movie_model_movie_studio add constraint fk_movie_model_movie_id foreign key (movie_model_movie_id) references movie_model;
alter table if exists movie_producer_model add constraint pk_movie_id foreign key (movie_id) references movie_model;
alter table if exists movie_producer_model add constraint pk_producer_id foreign key (producer_id) references producer_model;

create view producers_awards as
SELECT
    mm.movie_id || '-' || pm.producer_id as id,
    mm.*,
    pm.*

FROM MOVIE_MODEL mm
         inner join MOVIE_PRODUCER_MODEL mpm on mpm.movie_id = mm.MOVIE_ID

         inner join PRODUCER_MODEL pm on pm.PRODUCER_ID = mpm.PRODUCER_ID

where mm.winner is true

order by pm.PRODUCER_ID, MM.MOVIE_YEAR ASC;