\c tqos_database

-- Switch to the tq_admin user to drop the database for TQ objects.
SET ROLE tq_admin;

DROP SCHEMA tqos_asr CASCADE;

SET ROLE postgres;

\c postgres

-- Switch to the tq_admin user to drop the database for TQ objects.
SET ROLE tq_admin;

SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'tqos_database'
  AND pid <> pg_backend_pid();

DROP DATABASE tqos_database;
