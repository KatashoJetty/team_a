# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table app_group (
  groupid                   bigserial not null,
  groupname                 varchar(255),
  comment                   varchar(255),
  constraint pk_app_group primary key (groupid))
;

create table app_user (
  userid                    bigserial not null,
  username                  varchar(100) not null,
  password                  varchar(256) not null,
  salt                      CHAR(10) not null,
  constraint uq_app_user_username unique (username),
  constraint pk_app_user primary key (userid))
;

create table shop (
  no                        bigserial not null,
  shopid                    varchar(255),
  name                      varchar(255),
  url                       varchar(255),
  constraint pk_shop primary key (no))
;


create table app_user_app_group (
  app_user_userid                bigint not null,
  app_group_groupid              bigint not null,
  constraint pk_app_user_app_group primary key (app_user_userid, app_group_groupid))
;



alter table app_user_app_group add constraint fk_app_user_app_group_app_use_01 foreign key (app_user_userid) references app_user (userid);

alter table app_user_app_group add constraint fk_app_user_app_group_app_gro_02 foreign key (app_group_groupid) references app_group (groupid);

# --- !Downs

drop table if exists app_group cascade;

drop table if exists app_user_app_group cascade;

drop table if exists app_user cascade;

drop table if exists shop cascade;

