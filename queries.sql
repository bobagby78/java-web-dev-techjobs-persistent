## Part 1: Test it with SQL
CREATE TABLE job(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(250)
);

## Part 2: Test it with SQL
SELECT name FROM employer WHERE location LIKE "Saint Louis, MO";

## Part 3: Test it with SQL
DROP TABLE job;

## Part 4: Test it with SQL
return a list of the names and descriptions of all skills


SELECT job.name, skill.name, skill.description
FROM skill
INNER JOIN job_skills
ON skill.id = job_skills.skills_id
INNER JOIN job
ON job.id = job_skills.jobs_id
ORDER BY job.name;