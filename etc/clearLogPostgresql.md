set these config below:
C:\Program Files\PostgreSQL\{version}\data\postgresql.conf

logging_collector = off
log_filename = 'postgresql-PM.log'    //%p là PM or AM
log_truncate_on_rotation = on // turn on overwrite file
log_rotation_age = 720 // 720 minutes