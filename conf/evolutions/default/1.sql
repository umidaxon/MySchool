# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "school" ("id" SERIAL NOT NULL PRIMARY KEY,"ism" VARCHAR(254) DEFAULT '' NOT NULL,"familiya" VARCHAR(254) DEFAULT '' NOT NULL,"fan" VARCHAR(254) DEFAULT '' NOT NULL);

# --- !Downs

drop table "school";

