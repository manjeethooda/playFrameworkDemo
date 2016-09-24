# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product (
  primary_key                   bigint not null,
  product_id                    varchar(255),
  name                          varchar(255),
  description                   varchar(255),
  constraint pk_product primary key (primary_key)
);
create sequence product_seq;

create table product_tag (
  product_primary_key           bigint not null,
  tag_primary_key               bigint not null,
  constraint pk_product_tag primary key (product_primary_key,tag_primary_key)
);

create table stock_item (
  id                            bigint not null,
  warehouse_id                  bigint,
  product_primary_key           bigint,
  quantity                      bigint,
  constraint pk_stock_item primary key (id)
);
create sequence stock_item_seq;

create table tag (
  primary_key                   bigint not null,
  tag_id                        varchar(255),
  constraint pk_tag primary key (primary_key)
);
create sequence tag_seq;

create table warehouse (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_warehouse primary key (id)
);
create sequence warehouse_seq;

alter table product_tag add constraint fk_product_tag_product foreign key (product_primary_key) references product (primary_key) on delete restrict on update restrict;
create index ix_product_tag_product on product_tag (product_primary_key);

alter table product_tag add constraint fk_product_tag_tag foreign key (tag_primary_key) references tag (primary_key) on delete restrict on update restrict;
create index ix_product_tag_tag on product_tag (tag_primary_key);

alter table stock_item add constraint fk_stock_item_warehouse_id foreign key (warehouse_id) references warehouse (id) on delete restrict on update restrict;
create index ix_stock_item_warehouse_id on stock_item (warehouse_id);

alter table stock_item add constraint fk_stock_item_product_primary_key foreign key (product_primary_key) references product (primary_key) on delete restrict on update restrict;
create index ix_stock_item_product_primary_key on stock_item (product_primary_key);


# --- !Downs

alter table product_tag drop constraint if exists fk_product_tag_product;
drop index if exists ix_product_tag_product;

alter table product_tag drop constraint if exists fk_product_tag_tag;
drop index if exists ix_product_tag_tag;

alter table stock_item drop constraint if exists fk_stock_item_warehouse_id;
drop index if exists ix_stock_item_warehouse_id;

alter table stock_item drop constraint if exists fk_stock_item_product_primary_key;
drop index if exists ix_stock_item_product_primary_key;

drop table if exists product;
drop sequence if exists product_seq;

drop table if exists product_tag;

drop table if exists stock_item;
drop sequence if exists stock_item_seq;

drop table if exists tag;
drop sequence if exists tag_seq;

drop table if exists warehouse;
drop sequence if exists warehouse_seq;

