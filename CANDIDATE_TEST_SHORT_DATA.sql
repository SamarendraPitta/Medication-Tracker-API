-- Sample Data for Candidate Test (Short Version)
-- This file contains minimal test data for the short technical assessment

-- Clear existing data
TRUNCATE TABLE medications CASCADE;
TRUNCATE TABLE schedules CASCADE;
TRUNCATE TABLE intakes CASCADE;

-- Reset sequences
ALTER SEQUENCE medications_id_seq RESTART WITH 1;
ALTER SEQUENCE schedules_id_seq RESTART WITH 1;
ALTER SEQUENCE intakes_id_seq RESTART WITH 1;

-- Test Users (assuming users table exists)
INSERT INTO users (id, name) VALUES
(1, 'Test User 1'),
(2, 'Test User 2');

-- Sample Medications
INSERT INTO medications (id, name, dosage_form, strength) VALUES
(1, 'Lisinopril', 'TABLET', '10mg'),
(2, 'Metformin', 'TABLET', '500mg'),
(3, 'Levothyroxine', 'TABLET', '50mcg');

-- Sample Schedules
INSERT INTO schedules (id, medication_id, user_id, scheduled_time, days_of_week) VALUES
(1, 1, 1, '08:00:00', ARRAY[1,2,3,4,5,6,7]),  -- Lisinopril daily
(2, 2, 1, '09:00:00', ARRAY[1,3,5]),          -- Metformin MWF
(3, 3, 2, '07:00:00', ARRAY[1,2,3,4,5,6,7]);  -- Levothyroxine daily

-- Sample Intakes (last 30 days)
WITH RECURSIVE dates AS (
    SELECT CURRENT_DATE - INTERVAL '30 days' as date
    UNION ALL
    SELECT date + 1
    FROM dates
    WHERE date < CURRENT_DATE
)
INSERT INTO intakes (schedule_id, status, scheduled_for, taken_at)
SELECT 
    1 as schedule_id,
    CASE 
        WHEN RANDOM() > 0.2 THEN 'TAKEN'
        ELSE 'MISSED'
    END as status,
    date + TIME '08:00:00' as scheduled_for,
    CASE 
        WHEN RANDOM() > 0.2 THEN date + TIME '08:00:00' + (RANDOM() * INTERVAL '30 minutes')
        ELSE NULL
    END as taken_at
FROM dates
WHERE EXTRACT(DOW FROM date) = ANY(SELECT UNNEST(days_of_week) - 1 FROM schedules WHERE id = 1)
UNION ALL
SELECT 
    2 as schedule_id,
    CASE 
        WHEN RANDOM() > 0.3 THEN 'TAKEN'
        ELSE 'MISSED'
    END as status,
    date + TIME '09:00:00' as scheduled_for,
    CASE 
        WHEN RANDOM() > 0.3 THEN date + TIME '09:00:00' + (RANDOM() * INTERVAL '45 minutes')
        ELSE NULL
    END as taken_at
FROM dates
WHERE EXTRACT(DOW FROM date) = ANY(SELECT UNNEST(days_of_week) - 1 FROM schedules WHERE id = 2)
UNION ALL
SELECT 
    3 as schedule_id,
    CASE 
        WHEN RANDOM() > 0.1 THEN 'TAKEN'
        ELSE 'MISSED'
    END as status,
    date + TIME '07:00:00' as scheduled_for,
    CASE 
        WHEN RANDOM() > 0.1 THEN date + TIME '07:00:00' + (RANDOM() * INTERVAL '15 minutes')
        ELSE NULL
    END as taken_at
FROM dates
WHERE EXTRACT(DOW FROM date) = ANY(SELECT UNNEST(days_of_week) - 1 FROM schedules WHERE id = 3);

-- Add some specific test cases for compliance rate and moving average calculations
INSERT INTO intakes (schedule_id, status, scheduled_for, taken_at) VALUES
-- Perfect compliance week for testing
(1, 'TAKEN', CURRENT_DATE - INTERVAL '7 days' + TIME '08:00:00', CURRENT_DATE - INTERVAL '7 days' + TIME '08:05:00'),
(1, 'TAKEN', CURRENT_DATE - INTERVAL '6 days' + TIME '08:00:00', CURRENT_DATE - INTERVAL '6 days' + TIME '08:03:00'),
(1, 'TAKEN', CURRENT_DATE - INTERVAL '5 days' + TIME '08:00:00', CURRENT_DATE - INTERVAL '5 days' + TIME '08:07:00'),
(1, 'TAKEN', CURRENT_DATE - INTERVAL '4 days' + TIME '08:00:00', CURRENT_DATE - INTERVAL '4 days' + TIME '08:02:00'),
(1, 'TAKEN', CURRENT_DATE - INTERVAL '3 days' + TIME '08:00:00', CURRENT_DATE - INTERVAL '3 days' + TIME '08:10:00'),
(1, 'TAKEN', CURRENT_DATE - INTERVAL '2 days' + TIME '08:00:00', CURRENT_DATE - INTERVAL '2 days' + TIME '08:00:00'),
(1, 'TAKEN', CURRENT_DATE - INTERVAL '1 day' + TIME '08:00:00', CURRENT_DATE - INTERVAL '1 day' + TIME '08:15:00');
