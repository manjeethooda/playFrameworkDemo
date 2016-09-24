# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product (
  id                            bigint not null,
  name                          varchar(255),
  description                   varchar(255),
  tag_id                        bigint,
  constraint pk_product primary key (id)
);
create sequence product_seq;

create table stock_item (
  id                            bigint not null,
  warehouse_id                  bigint,
  product_id                    bigint,
  quantity                      bigint,
  constraint pk_stock_item primary key (id)
);
create sequence stock_item_seq;

create table tag (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_tag primary key (id)
);
create sequence tag_seq;

create table warehouse (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_warehouse primary key (id)
);
create sequence warehouse_seq;

alter table product add constraint fk_product_tag_id foreign key (tag_id) references tag (id) on delete restrict on update restrict;
create index ix_product_tag_id on product (tag_id);

alter table stock_item add constraint fk_stock_item_warehouse_id foreign key (warehouse_id) references warehouse (id) on delete restrict on update restrict;
create index ix_stock_item_warehouse_id on stock_item (warehouse_id);

alter table stock_item add constraint fk_stock_item_product_id foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_stock_item_product_id on stock_item (product_id);


# --- !Downs

alter table product drop constraint if exists fk_product_tag_id;
drop index if exists ix_product_tag_id;

alter table stock_item drop constraint if exists fk_stock_item_warehouse_id;
drop index if exists ix_stock_item_warehouse_id;

alter table stock_item drop constraint if exists fk_stock_item_product_id;
drop index if exists ix_stock_item_product_id;

drop table if exists product;
drop sequence if exists product_seq;

drop table if exists stock_item;
drop sequence if exists stock_item_seq;

drop table if exists tag;
drop sequence if exists tag_seq;

drop table if exists warehouse;
drop sequence if exists warehouse_seq;

