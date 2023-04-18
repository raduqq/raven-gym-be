ALTER TABLE discipline
    ADD CONSTRAINT unique_name_discipline
        UNIQUE(name);

ALTER TABLE coach
    ADD CONSTRAINT unique_name_coach
        UNIQUE(name);

ALTER TABLE student
    ADD CONSTRAINT unique_name_student
        UNIQUE(name);