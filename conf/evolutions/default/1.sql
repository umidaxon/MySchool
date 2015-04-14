# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "CLASS" ("ID" SERIAL NOT NULL PRIMARY KEY,"NAME" VARCHAR(254) DEFAULT '' NOT NULL);
create table "pupil" ("id" SERIAL NOT NULL PRIMARY KEY,"ISM" VARCHAR(254) DEFAULT '' NOT NULL,"FAMILIYA" VARCHAR(254) DEFAULT '' NOT NULL,"CLASS_ID" INTEGER NOT NULL);
alter table "pupil" add constraint "PUPIL_FK_CLASS_ID" foreign key("CLASS_ID") references "CLASS"("ID") on update NO ACTION on delete NO ACTION;

# --- !Downs

alter table "pupil" drop constraint "PUPIL_FK_CLASS_ID";
drop table "pupil";
drop table "CLASS";

