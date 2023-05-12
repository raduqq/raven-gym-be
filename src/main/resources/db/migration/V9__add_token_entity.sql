CREATE TABLE token (
   id INTEGER NOT NULL,
   token VARCHAR(255) UNIQUE,
   tokenType VARCHAR(255),
   revoked BOOLEAN,
   expired BOOLEAN,
   user_id INTEGER REFERENCES _user(id),
   PRIMARY KEY (id)
);