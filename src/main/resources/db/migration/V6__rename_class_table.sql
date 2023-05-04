ALTER TABLE class
    RENAME TO lesson;

ALTER TABLE lesson
    RENAME COLUMN title to name;

ALTER TABLE enrollment
    RENAME TO registration;

ALTER TABLE registration
    DROP CONSTRAINT enrollment_class_id_fkey,
    DROP COLUMN class_id,
    ADD COLUMN lesson_id INTEGER NOT NULL REFERENCES lesson(id);