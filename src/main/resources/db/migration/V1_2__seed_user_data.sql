INSERT INTO app_user(email, password) VALUES('onlyuser@domain.com', '$2a$12$1/XCtG3nCfqMPZm22cGwSORYEQOkiSF4pSJG7broqRC2Q/dJShwOu');
SET @only_user_id = LAST_INSERT_ID();

INSERT INTO app_user(email, password) VALUES('onlyadmin@domain.com', '$2a$12$1/XCtG3nCfqMPZm22cGwSORYEQOkiSF4pSJG7broqRC2Q/dJShwOu');
SET @only_admin_id = LAST_INSERT_ID();

INSERT INTO app_user(email, password) VALUES('user_admin@domain.com', '$2a$12$1/XCtG3nCfqMPZm22cGwSORYEQOkiSF4pSJG7broqRC2Q/dJShwOu');
SET @user_admin_id = LAST_INSERT_ID();

INSERT INTO app_user_role(name) VALUES ('ROLE_USER');
SET @user_role_id = LAST_INSERT_ID();

INSERT INTO app_user_role(name) VALUES ('ROLE_ADMIN');
SET @admin_role_id = LAST_INSERT_ID();

INSERT INTO app_users_roles(user_id, role_id) VALUES (@only_user_id, @user_role_id);
INSERT INTO app_users_roles(user_id, role_id) VALUES (@only_admin_id, @admin_role_id);
INSERT INTO app_users_roles(user_id, role_id) VALUES (@user_admin_id, @user_role_id);
INSERT INTO app_users_roles(user_id, role_id) VALUES (@user_admin_id, @admin_role_id);