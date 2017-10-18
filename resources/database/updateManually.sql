-- Column: public.ticket.create_date

-- ALTER TABLE public.ticket DROP COLUMN create_date;

ALTER TABLE public.customer
    ADD COLUMN create_date timestamp without time zone;

-- Index: ticketcreatedate

-- DROP INDEX public.ticketcreatedate;

CREATE INDEX customercreatedate
    ON public.customer USING btree
    (create_date)
    TABLESPACE pg_default;
