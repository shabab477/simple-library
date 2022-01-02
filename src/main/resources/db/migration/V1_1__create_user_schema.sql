CREATE TABLE app_user (
    id integer primary key auto_increment,
    email varchar(255) not null,
    password text not null
);

CREATE TABLE app_user_role (
   id integer primary key auto_increment,
   name varchar(255) not null
);

CREATE TABLE app_users_roles (
    user_id integer,
    role_id integer,

    CONSTRAINT pk_user_role_id PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_app_user FOREIGN KEY (user_id) REFERENCES app_user(id),
    CONSTRAINT fk_app_user_role FOREIGN KEY (role_id) REFERENCES app_user_role(id)
);
