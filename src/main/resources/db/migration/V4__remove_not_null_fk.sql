ALTER TABLE class
    ALTER COLUMN discipline_id
        DROP NOT NULl,
    ALTER COLUMN coach_id
        DROP NOT NULL;

ALTER TABLE student
    ALTER COLUMN preferred_discipline_id
        DROP NOT NULL;