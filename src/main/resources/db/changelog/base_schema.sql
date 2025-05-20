--liquibase formatted sql

--changeset me:1

CREATE TABLE IF NOT EXISTS users
(
    id       VARCHAR PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tasks
(
    id           UUID PRIMARY KEY,
    user_id      VARCHAR NOT NULL,
    title        VARCHAR(255),
    created_at   TIMESTAMP,
    target_date  TIMESTAMP,
    is_completed BOOLEAN,
    is_deleted   BOOLEAN
);

CREATE TABLE IF NOT EXISTS notifications
(
    id      UUID PRIMARY KEY,
    user_id VARCHAR NOT NULL,
    message TEXT,
    is_read BOOLEAN
);
