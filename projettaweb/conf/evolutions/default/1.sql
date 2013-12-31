# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table bookmark (
  id                        bigint auto_increment not null,
  title                     varchar(255),
  url                       varchar(255),
  details                   varchar(255),
  constraint pk_bookmark primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table bookmark;

SET FOREIGN_KEY_CHECKS=1;

