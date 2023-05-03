ALTER TABLE student
    DROP CONSTRAINT student_personal_record_id_fkey,
    DROP COLUMN personal_record_id,
    ADD COLUMN preferred_discipline_id INTEGER NOT NULL REFERENCES discipline(id);

DROP TABLE personal_record;