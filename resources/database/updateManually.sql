-- Column: public.customer.create_date

-- ALTER TABLE public.customer DROP COLUMN create_date;

ALTER TABLE public.customer
    ADD COLUMN create_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP;

-- Index: customercreatedate

-- DROP INDEX public.customercreatedate;

CREATE INDEX customercreatedate
    ON public.customer USING btree
    (create_date)
    TABLESPACE pg_default;

UPDATE public.customer SET create_date=CURRENT_TIMESTAMP - INTERVAL '2 DAYS';