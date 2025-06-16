create table tb_category (
    id bigint not null auto_increment,
    description varchar(255),
    name varchar(255),
    primary key (id)
) engine=InnoDB;

create table tb_product (
    available_quantity integer,
    value decimal(38,2),
    id binary(16) not null,
    description varchar(255),
    image_link varchar(255),
    name varchar(255),
    primary key (id)
) engine=InnoDB;

create table tb_permission(
    id bigint not null auto_increment,
    name varchar(255) not null,
    description varchar(255) not null,
    primary key (id)
) engine=InnoDB;

create table tb_group(
    id bigint not null auto_increment,
    name varchar(255) not null,
    description varchar(255) not null,
    primary key (id)
) engine=InnoDB;

create table tb_user(
    id binary(16) not null,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
) engine=InnoDB;

create table tb_product_category (
    category_id bigint not null,
    product_id binary(16) not null,
    constraint fk_product_category_product foreign key (product_id) references tb_product(id),
    constraint fk_product_category_category foreign key (category_id) references tb_category(id)
) engine=InnoDB;

create table tb_group_permission(
    group_id bigint not null,
    permission_id bigint not null,
    constraint fk_group_permission_group foreign key (group_id) references tb_group(id),
    constraint fk_group_permission_permission foreign key (permission_id) references tb_permission(id)
) engine=InnoDB;

create table tb_user_group(
    user_id binary(16) not null,
    group_id bigint not null,
    constraint fk_user_group_user foreign key (user_id) references tb_user(id),
    constraint fk_user_group_group foreign key (group_id) references tb_group(id)
) engine=InnoDB;


create index idx_product_name_description on tb_product (name, description);
create index idx_category_name_description on tb_category (name, description);
