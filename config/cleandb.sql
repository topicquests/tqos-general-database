--
-- Clean all data from the asr tables. To run this script:
--
-- sudo -u postgres psql -a -f <path-to>/cleandb.sql -d tqos_database
--

SET ROLE tq_admin;

DELETE FROM tqos_asr.sentences;
DELETE FROM tqos_asr.documents;
DELETE FROM tqos_asr.tuples;

\q



