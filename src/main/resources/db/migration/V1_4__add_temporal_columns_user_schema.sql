
ALTER TABLE app_user ADD COLUMN created_at datetime;
ALTER TABLE app_user ADD COLUMN updated_at datetime;

update app_user set created_at = now();

ALTER TABLE app_user MODIFY created_at datetime not null;
