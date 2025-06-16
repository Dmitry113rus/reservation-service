CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    phone      VARCHAR(20)  NOT NULL,
    email      VARCHAR(100) NOT NULL,
    username   VARCHAR(50)  NOT NULL UNIQUE,
    password   TEXT         NOT NULL,
    role       TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE admins
(
    id        SERIAL PRIMARY KEY,
    user_id   INT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE reservations
(
    id               SERIAL PRIMARY KEY,
    user_id          INT         NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    reservation_date DATE        NOT NULL,
    reservation_time TIME        NOT NULL,
    guests_count     INT         NOT NULL CHECK (guests_count > 0),
    contact_phone    VARCHAR(20) NOT NULL,
    status           VARCHAR(20) NOT NULL DEFAULT 'pending',
    admin_comment    TEXT,
    created_at       TIMESTAMP            DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tables
(
    id           SERIAL PRIMARY KEY,
    table_number INT NOT NULL UNIQUE,
    capacity     INT NOT NULL CHECK (capacity > 0)
);

CREATE TABLE reservation_tables
(
    reservation_id INT REFERENCES reservations (id) ON DELETE CASCADE,
    table_id       INT REFERENCES tables (id) ON DELETE CASCADE,
    PRIMARY KEY (reservation_id, table_id)
);

INSERT INTO public.users (first_name, last_name, phone, email, username, password, role, created_at)
VALUES ('Admin', 'Admin', '+7(000)-000-00-00', 'admin@example.com', 'admin',
        '$2a$10$ymjpn/7R0lwJDOm4QvzCn.rI5ijaDsRJm1WsWVcZXSgxoRIvJUDTi', 'ADMIN');
