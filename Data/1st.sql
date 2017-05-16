--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

-- Started on 2017-04-17 10:39:50 ICT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 3431 (class 0 OID 23736)
-- Dependencies: 302
-- Data for Name: shift; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO shift VALUES (1, 'General', '1970-01-01 00:00:00', '1970-01-01 23:59:00', 86340000);


--
-- TOC entry 3446 (class 0 OID 23800)
-- Dependencies: 317
-- Data for Name: terminal; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO terminal VALUES (162, '162', 'd25fafcf-3459-418d-970a-d512518c9f13', 0, 0, true, false, false, NULL, 0, NULL);


--
-- TOC entry 3471 (class 0 OID 23930)
-- Dependencies: 342
-- Data for Name: user_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_type VALUES (1, 'Administrator');
INSERT INTO user_type VALUES (2, 'MANAGER');
INSERT INTO user_type VALUES (3, 'CASHIER');
INSERT INTO user_type VALUES (4, 'SR. CASHIER');


--
-- TOC entry 3468 (class 0 OID 23913)
-- Dependencies: 339
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO users VALUES (2, 124, '2222', 'Lisa', 'Carol', '124', 0, false, NULL, NULL, NULL, false, false, true, NULL, NULL, 2);
INSERT INTO users VALUES (3, 125, '3333', 'Janet', 'Ann', '125', 0, false, NULL, NULL, NULL, false, false, true, NULL, NULL, 3);
INSERT INTO users VALUES (4, 126, '7777', 'John', 'Doe', '126', 0, false, NULL, NULL, NULL, false, false, true, NULL, NULL, 4);
INSERT INTO users VALUES (5, 127, '8888', 'Poll', 'Brien', '127', 0, false, NULL, NULL, NULL, true, false, true, NULL, NULL, 4);
INSERT INTO users VALUES (1, 123, '1111', 'Admin', 'System', '123', 0, true, '2017-04-17 10:38:52.159', NULL, NULL, false, false, true, 1, 162, 1);


--
-- TOC entry 3315 (class 0 OID 23236)
-- Dependencies: 186
-- Data for Name: action_history; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3484 (class 0 OID 0)
-- Dependencies: 185
-- Name: action_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('action_history_id_seq', 1, false);


--
-- TOC entry 3317 (class 0 OID 23247)
-- Dependencies: 188
-- Data for Name: attendence_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO attendence_history VALUES (1, '2017-04-17 10:38:52.159', NULL, 10, NULL, false, 1, 1, 162);


--
-- TOC entry 3485 (class 0 OID 0)
-- Dependencies: 187
-- Name: attendence_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('attendence_history_id_seq', 1, true);


--
-- TOC entry 3319 (class 0 OID 23255)
-- Dependencies: 190
-- Data for Name: cash_drawer; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3486 (class 0 OID 0)
-- Dependencies: 189
-- Name: cash_drawer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cash_drawer_id_seq', 1, false);


--
-- TOC entry 3321 (class 0 OID 23263)
-- Dependencies: 192
-- Data for Name: cash_drawer_reset_history; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3487 (class 0 OID 0)
-- Dependencies: 191
-- Name: cash_drawer_reset_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cash_drawer_reset_history_id_seq', 1, false);


--
-- TOC entry 3323 (class 0 OID 23271)
-- Dependencies: 194
-- Data for Name: cooking_instruction; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3488 (class 0 OID 0)
-- Dependencies: 193
-- Name: cooking_instruction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cooking_instruction_id_seq', 1, false);


--
-- TOC entry 3325 (class 0 OID 23279)
-- Dependencies: 196
-- Data for Name: coupon_and_discount; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO coupon_and_discount VALUES (1, 'Buy 1 and get 1 free', 1, NULL, 0, true, 2, 0, 100, NULL, true, false, false, true, NULL);
INSERT INTO coupon_and_discount VALUES (2, 'Buy 2 and get 1 free', 1, NULL, 0, true, 3, 0, 100, NULL, true, true, false, true, NULL);
INSERT INTO coupon_and_discount VALUES (3, '10% Off', 1, NULL, 0, true, 1, 0, 10, NULL, true, false, false, true, NULL);


--
-- TOC entry 3489 (class 0 OID 0)
-- Dependencies: 195
-- Name: coupon_and_discount_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('coupon_and_discount_id_seq', 3, true);


--
-- TOC entry 3327 (class 0 OID 23289)
-- Dependencies: 198
-- Data for Name: currency; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO currency VALUES (1, NULL, 'USD', '$', 1, 0, 0, 0, true);
INSERT INTO currency VALUES (2, NULL, 'EUR', 'E', 0.800000000000000044, 0, 0, 0, false);
INSERT INTO currency VALUES (3, NULL, 'BRL', 'B', 3.4700000000000002, 0, 0, 0, false);
INSERT INTO currency VALUES (4, NULL, 'ARS', 'P', 13.8900000000000006, 0, 0, 0, false);
INSERT INTO currency VALUES (5, NULL, 'PYG', 'P', 5639.77999999999975, 0, 0, 0, false);


--
-- TOC entry 3348 (class 0 OID 23380)
-- Dependencies: 219
-- Data for Name: drawer_pull_report; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3329 (class 0 OID 23297)
-- Dependencies: 200
-- Data for Name: currency_balance; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3490 (class 0 OID 0)
-- Dependencies: 199
-- Name: currency_balance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('currency_balance_id_seq', 1, false);


--
-- TOC entry 3491 (class 0 OID 0)
-- Dependencies: 197
-- Name: currency_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('currency_id_seq', 5, true);


--
-- TOC entry 3334 (class 0 OID 23324)
-- Dependencies: 205
-- Data for Name: custom_payment; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3492 (class 0 OID 0)
-- Dependencies: 204
-- Name: custom_payment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('custom_payment_id_seq', 1, false);


--
-- TOC entry 3331 (class 0 OID 23305)
-- Dependencies: 202
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3493 (class 0 OID 0)
-- Dependencies: 201
-- Name: customer_auto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('customer_auto_id_seq', 1, false);


--
-- TOC entry 3332 (class 0 OID 23314)
-- Dependencies: 203
-- Data for Name: customer_properties; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3336 (class 0 OID 23332)
-- Dependencies: 207
-- Data for Name: data_update_info; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3494 (class 0 OID 0)
-- Dependencies: 206
-- Name: data_update_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('data_update_info_id_seq', 1, false);


--
-- TOC entry 3338 (class 0 OID 23340)
-- Dependencies: 209
-- Data for Name: delivery_address; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3495 (class 0 OID 0)
-- Dependencies: 208
-- Name: delivery_address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('delivery_address_id_seq', 1, false);


--
-- TOC entry 3340 (class 0 OID 23348)
-- Dependencies: 211
-- Data for Name: delivery_charge; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3496 (class 0 OID 0)
-- Dependencies: 210
-- Name: delivery_charge_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('delivery_charge_id_seq', 1, false);


--
-- TOC entry 3342 (class 0 OID 23356)
-- Dependencies: 213
-- Data for Name: delivery_configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO delivery_configuration VALUES (1, 'MILE', NULL, false);


--
-- TOC entry 3497 (class 0 OID 0)
-- Dependencies: 212
-- Name: delivery_configuration_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('delivery_configuration_id_seq', 1, true);


--
-- TOC entry 3344 (class 0 OID 23364)
-- Dependencies: 215
-- Data for Name: delivery_instruction; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3498 (class 0 OID 0)
-- Dependencies: 214
-- Name: delivery_instruction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('delivery_instruction_id_seq', 1, false);


--
-- TOC entry 3346 (class 0 OID 23372)
-- Dependencies: 217
-- Data for Name: drawer_assigned_history; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3499 (class 0 OID 0)
-- Dependencies: 216
-- Name: drawer_assigned_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('drawer_assigned_history_id_seq', 1, false);


--
-- TOC entry 3500 (class 0 OID 0)
-- Dependencies: 218
-- Name: drawer_pull_report_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('drawer_pull_report_id_seq', 1, false);


--
-- TOC entry 3349 (class 0 OID 23386)
-- Dependencies: 220
-- Data for Name: drawer_pull_report_voidtickets; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3351 (class 0 OID 23394)
-- Dependencies: 222
-- Data for Name: employee_in_out_history; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3501 (class 0 OID 0)
-- Dependencies: 221
-- Name: employee_in_out_history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('employee_in_out_history_id_seq', 1, false);


--
-- TOC entry 3353 (class 0 OID 23402)
-- Dependencies: 224
-- Data for Name: global_config; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3502 (class 0 OID 0)
-- Dependencies: 223
-- Name: global_config_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('global_config_id_seq', 1, false);


--
-- TOC entry 3355 (class 0 OID 23412)
-- Dependencies: 226
-- Data for Name: gratuity; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3503 (class 0 OID 0)
-- Dependencies: 225
-- Name: gratuity_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('gratuity_id_seq', 1, false);


--
-- TOC entry 3357 (class 0 OID 23420)
-- Dependencies: 228
-- Data for Name: inventory_group; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3504 (class 0 OID 0)
-- Dependencies: 227
-- Name: inventory_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('inventory_group_id_seq', 1, false);


--
-- TOC entry 3371 (class 0 OID 23485)
-- Dependencies: 242
-- Data for Name: inventory_warehouse; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3361 (class 0 OID 23436)
-- Dependencies: 232
-- Data for Name: inventory_location; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3369 (class 0 OID 23474)
-- Dependencies: 240
-- Data for Name: inventory_vendor; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3408 (class 0 OID 23636)
-- Dependencies: 279
-- Data for Name: packaging_unit; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3359 (class 0 OID 23428)
-- Dependencies: 230
-- Data for Name: inventory_item; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3505 (class 0 OID 0)
-- Dependencies: 229
-- Name: inventory_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('inventory_item_id_seq', 1, false);


--
-- TOC entry 3506 (class 0 OID 0)
-- Dependencies: 231
-- Name: inventory_location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('inventory_location_id_seq', 1, false);


--
-- TOC entry 3363 (class 0 OID 23444)
-- Dependencies: 234
-- Data for Name: inventory_meta_code; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3507 (class 0 OID 0)
-- Dependencies: 233
-- Name: inventory_meta_code_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('inventory_meta_code_id_seq', 1, false);


--
-- TOC entry 3424 (class 0 OID 23707)
-- Dependencies: 295
-- Data for Name: purchase_order; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3365 (class 0 OID 23455)
-- Dependencies: 236
-- Data for Name: inventory_transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3508 (class 0 OID 0)
-- Dependencies: 235
-- Name: inventory_transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('inventory_transaction_id_seq', 1, false);


--
-- TOC entry 3367 (class 0 OID 23463)
-- Dependencies: 238
-- Data for Name: inventory_unit; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3509 (class 0 OID 0)
-- Dependencies: 237
-- Name: inventory_unit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('inventory_unit_id_seq', 1, false);


--
-- TOC entry 3510 (class 0 OID 0)
-- Dependencies: 239
-- Name: inventory_vendor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('inventory_vendor_id_seq', 1, false);


--
-- TOC entry 3511 (class 0 OID 0)
-- Dependencies: 241
-- Name: inventory_warehouse_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('inventory_warehouse_id_seq', 1, false);


--
-- TOC entry 3388 (class 0 OID 23549)
-- Dependencies: 259
-- Data for Name: menu_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO menu_category VALUES (1, 'APPETIZERS', NULL, true, false, 9999, 0, 0);
INSERT INTO menu_category VALUES (2, 'BEER & WINE', NULL, true, true, 9999, 0, 0);
INSERT INTO menu_category VALUES (3, 'BEVERAGE & DRINKS', NULL, true, true, 9999, 0, 0);
INSERT INTO menu_category VALUES (4, 'BREAKFAST', NULL, true, false, 9999, 0, 0);
INSERT INTO menu_category VALUES (5, 'BUFFET', NULL, false, false, 9999, 0, 0);
INSERT INTO menu_category VALUES (6, 'DESSERT & ICECRM', NULL, true, false, 9999, 0, 0);
INSERT INTO menu_category VALUES (7, 'FAVORITES', NULL, true, false, 9999, 0, 0);
INSERT INTO menu_category VALUES (8, 'KIDS ', NULL, true, false, 9999, 0, 0);
INSERT INTO menu_category VALUES (9, 'LUNCH N DINNER', NULL, true, false, 9999, 0, 0);
INSERT INTO menu_category VALUES (10, 'SIDES', NULL, true, false, 9999, 0, 0);


--
-- TOC entry 3390 (class 0 OID 23557)
-- Dependencies: 261
-- Data for Name: menu_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO menu_group VALUES (1, 'BEERS', NULL, true, 9999, 0, 0, 2);
INSERT INTO menu_group VALUES (2, 'CEREALS & MUFF', NULL, true, 9999, 0, 0, 4);
INSERT INTO menu_group VALUES (3, 'COLD BEVERAGE', NULL, true, 9999, 0, 0, 3);
INSERT INTO menu_group VALUES (4, 'FAVOURITE', NULL, true, 9999, 0, 0, 4);
INSERT INTO menu_group VALUES (5, 'FRENCH FRIES', NULL, true, 9999, 0, 0, 1);
INSERT INTO menu_group VALUES (6, 'HOT DRINKS', NULL, true, 9999, 0, 0, 3);
INSERT INTO menu_group VALUES (7, 'JUICES', NULL, true, 9999, 0, 0, 3);
INSERT INTO menu_group VALUES (8, 'KIDS MEAL', NULL, true, 9999, 0, 0, 8);
INSERT INTO menu_group VALUES (9, 'ONION RINGS', NULL, false, 9999, 0, 0, 1);
INSERT INTO menu_group VALUES (10, 'PANCAKE N SUCH', NULL, true, 9999, 0, 0, 4);
INSERT INTO menu_group VALUES (11, 'REDS', NULL, true, 9999, 0, 0, 2);
INSERT INTO menu_group VALUES (12, 'SANDWITCH PLATTER', NULL, true, 9999, 0, 0, 9);
INSERT INTO menu_group VALUES (13, 'SIDES', NULL, true, 9999, 0, 0, 10);
INSERT INTO menu_group VALUES (14, 'SILE PLATES', NULL, true, 9999, 0, 0, 4);
INSERT INTO menu_group VALUES (15, 'TRADITIONAL B.FAST', NULL, true, 9999, 0, 0, 4);
INSERT INTO menu_group VALUES (16, 'WHITES', NULL, true, 9999, 0, 0, 2);
INSERT INTO menu_group VALUES (17, 'WHOLESOME', NULL, true, 9999, 0, 0, 4);


--
-- TOC entry 3421 (class 0 OID 23694)
-- Dependencies: 292
-- Data for Name: printer_group; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3445 (class 0 OID 23794)
-- Dependencies: 316
-- Data for Name: tax; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tax VALUES (1, 'US', 6);


--
-- TOC entry 3392 (class 0 OID 23565)
-- Dependencies: 263
-- Data for Name: menu_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO menu_item VALUES (1, '1 EGG BREAKFAST', NULL, NULL, '1 EGG BREAKFAST', NULL, 0, 0, 1, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 4, 1, NULL, NULL);
INSERT INTO menu_item VALUES (2, '2 EGG N BISCUIT', NULL, NULL, '2 EGG N BISCUIT', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 4, NULL, NULL, NULL);
INSERT INTO menu_item VALUES (3, '2 EGG SANDWITCH', NULL, NULL, '2 EGG SANDWITCH', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 4, NULL, NULL, NULL);
INSERT INTO menu_item VALUES (4, '4 VINES CALIF', NULL, NULL, '4 VINES CALIF', NULL, 0, 0, 0, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 11, NULL, NULL, NULL);
INSERT INTO menu_item VALUES (5, 'A 2 Z CHARDONNAY', NULL, NULL, 'A 2 Z CHARDONNAY', NULL, 0, 0, 3, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 16, 1, NULL, NULL);
INSERT INTO menu_item VALUES (6, 'APPLE DIPPER', NULL, NULL, 'APPLE DIPPER', NULL, 0, 0, 1, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 13, 1, NULL, NULL);
INSERT INTO menu_item VALUES (7, 'APPLE JUICE', NULL, NULL, 'APPLE JUICE', NULL, 0, 0, 0.5, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 7, 1, NULL, NULL);
INSERT INTO menu_item VALUES (8, 'ASSORT CEREAL', NULL, NULL, 'ASSORT CEREAL', NULL, 0, 0, 1.5, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 2, 1, NULL, NULL);
INSERT INTO menu_item VALUES (9, 'AVALON NAPA', NULL, NULL, 'AVALON NAPA', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 11, 1, NULL, NULL);
INSERT INTO menu_item VALUES (10, 'BELLYS SEASONAL', NULL, NULL, 'BELLYS SEASONAL', NULL, 0, 0, 4, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 1, 1, NULL, NULL);
INSERT INTO menu_item VALUES (11, 'CEASAR SALAD', NULL, NULL, 'CEASAR SALAD', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 13, 1, NULL, NULL);
INSERT INTO menu_item VALUES (12, 'CHICK BLT', NULL, NULL, 'CHICK BLT', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 12, 1, NULL, NULL);
INSERT INTO menu_item VALUES (13, 'CHICK TENDER KIDS MEAL', NULL, NULL, 'CHICK TENDER KIDS MEAL', NULL, 0, 0, 4, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 8, 1, NULL, NULL);
INSERT INTO menu_item VALUES (14, 'CHOC MILK', NULL, NULL, 'CHOC MILK', NULL, 0, 0, 2.25, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 3, 1, NULL, NULL);
INSERT INTO menu_item VALUES (15, 'CUPPOCINO', NULL, NULL, 'CUPPOCINO', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 6, 1, NULL, NULL);
INSERT INTO menu_item VALUES (16, 'DBL MEAT BRK', NULL, NULL, 'DBL MEAT BRK', NULL, 0, 0, 3, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 15, 1, NULL, NULL);
INSERT INTO menu_item VALUES (17, 'DISENO MALBEC', NULL, NULL, 'DISENO MALBEC', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 11, 1, NULL, NULL);
INSERT INTO menu_item VALUES (18, 'FELLUGO ITALY', NULL, NULL, 'FELLUGO ITALY', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 11, 1, NULL, NULL);
INSERT INTO menu_item VALUES (19, 'FIRESTEED OREGON', NULL, NULL, 'FIRESTEED OREGON', NULL, 0, 0, 4, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 16, 1, NULL, NULL);
INSERT INTO menu_item VALUES (20, 'GO GO SQUEEZE', NULL, NULL, 'GO GO SQUEEZE', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 13, 1, NULL, NULL);
INSERT INTO menu_item VALUES (21, 'GRANDPA CNTRY B.FAST', NULL, NULL, 'GRANDPA CNTRY B.FAST', NULL, 0, 0, 3, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 15, 1, NULL, NULL);
INSERT INTO menu_item VALUES (22, 'GRAVY N BISCUIT', NULL, NULL, 'GRAVY N BISCUIT', NULL, 0, 0, 1, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 4, 1, NULL, NULL);
INSERT INTO menu_item VALUES (23, 'GRILLED CHICKEN', NULL, NULL, 'GRILLED CHICKEN', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 12, 1, NULL, NULL);
INSERT INTO menu_item VALUES (24, 'HAMBURGER HALFPOUND', NULL, NULL, 'HAMBURGER HALFPOUND', NULL, 0, 0, 3, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 12, 1, NULL, NULL);
INSERT INTO menu_item VALUES (25, 'HAMMER COFFEE', NULL, NULL, 'HAMMER COFFEE', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 6, 1, NULL, NULL);
INSERT INTO menu_item VALUES (26, 'HASHBROWN COMBO', NULL, NULL, 'HASHBROWN COMBO', NULL, 0, 0, 1, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 4, 1, NULL, NULL);
INSERT INTO menu_item VALUES (27, 'ICE TEA', NULL, NULL, 'ICE TEA', NULL, 0, 0, 0, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 3, 1, NULL, NULL);
INSERT INTO menu_item VALUES (28, 'KENWOOD YULUPA', NULL, NULL, 'KENWOOD YULUPA', NULL, 0, 0, 3, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 16, 1, NULL, NULL);
INSERT INTO menu_item VALUES (29, 'LEMONADE', NULL, NULL, 'LEMONADE', NULL, 0, 0, 1, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 3, 1, NULL, NULL);
INSERT INTO menu_item VALUES (30, 'MEAT BISCUIT N HASHBROWN', NULL, NULL, 'MEAT BISCUIT N HASHBROWN', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 4, 1, NULL, NULL);
INSERT INTO menu_item VALUES (31, 'MICHELOB GOLDEN', NULL, NULL, 'MICHELOB GOLDEN', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 1, 1, NULL, NULL);
INSERT INTO menu_item VALUES (32, 'MNT DEW', NULL, NULL, 'MNT DEW', NULL, 0, 0, 1, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 3, 1, NULL, NULL);
INSERT INTO menu_item VALUES (33, 'MOZZARELLA STICK', NULL, NULL, 'MOZZARELLA STICK', NULL, 0, 0, 0, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 13, 1, NULL, NULL);
INSERT INTO menu_item VALUES (34, 'OATMEAL ', NULL, NULL, 'OATMEAL ', NULL, 0, 0, 1.5, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 2, 1, NULL, NULL);
INSERT INTO menu_item VALUES (35, 'OLD TIMER B.FAST', NULL, NULL, 'OLD TIMER B.FAST', NULL, 0, 0, 2.5, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 15, 1, NULL, NULL);
INSERT INTO menu_item VALUES (36, 'OVN ROAST TURKEY', NULL, NULL, 'OVN ROAST TURKEY', NULL, 0, 0, 3, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 12, 1, NULL, NULL);
INSERT INTO menu_item VALUES (37, 'SCHELLS FIREBRICH AMBER', NULL, NULL, 'SCHELLS FIREBRICH AMBER', NULL, 0, 0, 4, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 1, 1, NULL, NULL);
INSERT INTO menu_item VALUES (38, 'SMK HOUS B.FAST', NULL, NULL, 'SMK HOUS B.FAST', NULL, 0, 0, 2.5, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 15, 1, NULL, NULL);
INSERT INTO menu_item VALUES (39, 'SODA', NULL, NULL, 'SODA', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 3, 1, NULL, NULL);
INSERT INTO menu_item VALUES (40, 'SOURDOUGH TOAST', NULL, NULL, 'SOURDOUGH TOAST', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 4, 1, NULL, NULL);
INSERT INTO menu_item VALUES (41, 'STEELHEAD', NULL, NULL, 'STEELHEAD', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 11, 1, NULL, NULL);
INSERT INTO menu_item VALUES (42, 'STELLA ARTOIS ', NULL, NULL, 'STELLA ARTOIS ', NULL, 0, 0, 3, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 1, 1, NULL, NULL);
INSERT INTO menu_item VALUES (43, 'SUMMIT PALE ALE', NULL, NULL, 'SUMMIT PALE ALE', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 1, 1, NULL, NULL);
INSERT INTO menu_item VALUES (44, 'SUNRISE SAMPLER', NULL, NULL, 'SUNRISE SAMPLER', NULL, 0, 0, 3.5, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 15, 1, NULL, NULL);
INSERT INTO menu_item VALUES (45, 'SURLY FURIOUS', NULL, NULL, 'SURLY FURIOUS', NULL, 0, 0, 4, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 1, 1, NULL, NULL);
INSERT INTO menu_item VALUES (46, 'TANGLE OAKS', NULL, NULL, 'TANGLE OAKS', NULL, 0, 0, 3, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 16, 1, NULL, NULL);
INSERT INTO menu_item VALUES (47, 'TRIADE ITALIAN', NULL, NULL, 'TRIADE ITALIAN', NULL, 0, 0, 3, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 16, 1, NULL, NULL);
INSERT INTO menu_item VALUES (48, 'VANILLA YOUGURT', NULL, NULL, 'VANILLA YOUGURT', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 13, 1, NULL, NULL);
INSERT INTO menu_item VALUES (49, 'VIENNA DECAF', NULL, NULL, 'VIENNA DECAF', NULL, 0, 0, 1, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 6, 1, NULL, NULL);
INSERT INTO menu_item VALUES (50, 'VINA MAYOR', NULL, NULL, 'VINA MAYOR', NULL, 0, 0, 2, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 11, 1, NULL, NULL);
INSERT INTO menu_item VALUES (51, 'WAIRAU RIVER', NULL, NULL, 'WAIRAU RIVER', NULL, 0, 0, 4, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 16, 1, NULL, NULL);
INSERT INTO menu_item VALUES (52, 'YOGURT PRF', NULL, NULL, 'YOGURT PRF', NULL, 0, 0, 2.5, 0, true, false, 9999, NULL, NULL, NULL, false, false, false, 0, 2, 1, NULL, NULL);


--
-- TOC entry 3406 (class 0 OID 23626)
-- Dependencies: 277
-- Data for Name: order_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO order_type VALUES (1, 'DINE IN', true, true, false, true, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false);
INSERT INTO order_type VALUES (2, 'TAKE OUT', true, false, false, true, true, true, false, false, false, true, false, false, false, false, false, false, false, false, false);
INSERT INTO order_type VALUES (3, 'RETAIL', true, false, false, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false);
INSERT INTO order_type VALUES (4, 'HOME DELIVERY', true, false, false, true, false, false, true, true, false, true, false, false, false, false, false, false, false, false, false);


--
-- TOC entry 3372 (class 0 OID 23491)
-- Dependencies: 243
-- Data for Name: item_order_type; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3374 (class 0 OID 23496)
-- Dependencies: 245
-- Data for Name: kitchen_ticket; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3377 (class 0 OID 23513)
-- Dependencies: 248
-- Data for Name: kit_ticket_table_num; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3512 (class 0 OID 0)
-- Dependencies: 244
-- Name: kitchen_ticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('kitchen_ticket_id_seq', 1, false);


--
-- TOC entry 3376 (class 0 OID 23504)
-- Dependencies: 247
-- Data for Name: kitchen_ticket_item; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3513 (class 0 OID 0)
-- Dependencies: 246
-- Name: kitchen_ticket_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('kitchen_ticket_item_id_seq', 1, false);


--
-- TOC entry 3514 (class 0 OID 0)
-- Dependencies: 258
-- Name: menu_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('menu_category_id_seq', 10, true);


--
-- TOC entry 3515 (class 0 OID 0)
-- Dependencies: 260
-- Name: menu_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('menu_group_id_seq', 17, true);


--
-- TOC entry 3516 (class 0 OID 0)
-- Dependencies: 262
-- Name: menu_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('menu_item_id_seq', 52, true);


--
-- TOC entry 3393 (class 0 OID 23574)
-- Dependencies: 264
-- Data for Name: menu_item_properties; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3395 (class 0 OID 23581)
-- Dependencies: 266
-- Data for Name: menu_item_size; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO menu_item_size VALUES (1, 'SMALL', 'SMALL', NULL, 0, 0, false);
INSERT INTO menu_item_size VALUES (2, 'MEDIUM', 'MEDIUM', NULL, 1, 0, false);
INSERT INTO menu_item_size VALUES (3, 'LARGE', 'LARGE', NULL, 2, 0, false);


--
-- TOC entry 3517 (class 0 OID 0)
-- Dependencies: 265
-- Name: menu_item_size_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('menu_item_size_id_seq', 3, true);


--
-- TOC entry 3396 (class 0 OID 23587)
-- Dependencies: 267
-- Data for Name: menu_item_terminal_ref; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3400 (class 0 OID 23600)
-- Dependencies: 271
-- Data for Name: menu_modifier_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO menu_modifier_group VALUES (1, 'APPLE SLICE', NULL, false, false, false);
INSERT INTO menu_modifier_group VALUES (2, 'BACON & SAUSAGE', NULL, false, false, false);
INSERT INTO menu_modifier_group VALUES (3, 'BISCUITS', NULL, false, false, false);
INSERT INTO menu_modifier_group VALUES (4, 'BUTTER', NULL, false, false, false);
INSERT INTO menu_modifier_group VALUES (5, 'CHEESE', NULL, false, false, false);
INSERT INTO menu_modifier_group VALUES (6, 'EGGS', NULL, false, false, false);
INSERT INTO menu_modifier_group VALUES (7, 'MEAT', NULL, false, false, false);
INSERT INTO menu_modifier_group VALUES (8, 'POTATO', NULL, false, false, false);
INSERT INTO menu_modifier_group VALUES (9, 'WHEAT', NULL, false, false, false);


--
-- TOC entry 3398 (class 0 OID 23592)
-- Dependencies: 269
-- Data for Name: menu_modifier; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO menu_modifier VALUES (1, 'AMERICAN', NULL, 0, 0.5, 9999, NULL, NULL, false, false, true, false, false, 5, 1);
INSERT INTO menu_modifier VALUES (2, 'BACON', NULL, 0.5, 0, 9999, NULL, NULL, false, false, true, false, false, 2, 1);
INSERT INTO menu_modifier VALUES (3, 'BLEU CHEESE', NULL, 0, 0.5, 9999, NULL, NULL, false, false, true, false, false, 5, 1);
INSERT INTO menu_modifier VALUES (4, 'Energy Plus', NULL, 0, 0, 9999, NULL, NULL, false, false, true, false, false, 3, 1);
INSERT INTO menu_modifier VALUES (5, 'FRIED APPLE', NULL, 0, 0, 9999, NULL, NULL, false, false, true, false, false, 2, 1);
INSERT INTO menu_modifier VALUES (6, 'FRIED EGG', NULL, 0, 0.5, 9999, NULL, NULL, false, false, true, false, false, 6, 1);
INSERT INTO menu_modifier VALUES (7, 'HASHB CASSEROLE', NULL, 0, 0, 9999, NULL, NULL, false, false, true, false, false, 2, 1);
INSERT INTO menu_modifier VALUES (8, 'HASHBROWN', NULL, 0, 0.5, 9999, NULL, NULL, false, false, true, false, false, 8, 1);
INSERT INTO menu_modifier VALUES (9, 'OMLETTE', NULL, 0, 0.5, 9999, NULL, NULL, false, false, true, false, false, 6, 1);
INSERT INTO menu_modifier VALUES (10, 'PAPER JACK', NULL, 0, 0.5, 9999, NULL, NULL, false, false, true, false, false, 5, 1);
INSERT INTO menu_modifier VALUES (11, 'PROVOLON', NULL, 0.5, 0, 9999, NULL, NULL, false, false, true, false, false, 5, 1);
INSERT INTO menu_modifier VALUES (12, 'SAUSAGE', NULL, 0, 0.5, 9999, NULL, NULL, false, false, true, false, false, 2, 1);
INSERT INTO menu_modifier VALUES (13, 'SCRAMBLED', NULL, 0, 0.5, 9999, NULL, NULL, false, false, true, false, false, 6, 1);
INSERT INTO menu_modifier VALUES (14, 'SMOKY SAUSAGE', NULL, 0, 0, 9999, NULL, NULL, false, false, true, false, false, 2, 1);
INSERT INTO menu_modifier VALUES (15, 'SUN SIDE UP', NULL, 0, 0.5, 9999, NULL, NULL, false, false, true, false, false, 6, 1);
INSERT INTO menu_modifier VALUES (16, 'THICK BACON', NULL, 0, 0, 9999, NULL, NULL, false, false, true, false, false, 2, 1);


--
-- TOC entry 3518 (class 0 OID 0)
-- Dependencies: 270
-- Name: menu_modifier_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('menu_modifier_group_id_seq', 9, true);


--
-- TOC entry 3519 (class 0 OID 0)
-- Dependencies: 268
-- Name: menu_modifier_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('menu_modifier_id_seq', 16, true);


--
-- TOC entry 3401 (class 0 OID 23606)
-- Dependencies: 272
-- Data for Name: menu_modifier_properties; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3378 (class 0 OID 23516)
-- Dependencies: 249
-- Data for Name: menucategory_discount; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3379 (class 0 OID 23519)
-- Dependencies: 250
-- Data for Name: menugroup_discount; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3380 (class 0 OID 23522)
-- Dependencies: 251
-- Data for Name: menuitem_discount; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3382 (class 0 OID 23527)
-- Dependencies: 253
-- Data for Name: menuitem_modifiergroup; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO menuitem_modifiergroup VALUES (1, 1, 1, 0, 6, 1);
INSERT INTO menuitem_modifiergroup VALUES (2, 1, 2, 0, 2, 1);
INSERT INTO menuitem_modifiergroup VALUES (3, 1, 2, 0, 6, 2);
INSERT INTO menuitem_modifiergroup VALUES (4, 1, 0, 0, 3, 2);
INSERT INTO menuitem_modifiergroup VALUES (5, 0, 1, 0, 4, 2);
INSERT INTO menuitem_modifiergroup VALUES (8, 2, 2, 0, 6, 3);
INSERT INTO menuitem_modifiergroup VALUES (9, 0, 1, 0, 5, 3);
INSERT INTO menuitem_modifiergroup VALUES (6, 0, 1, 0, 1, 26);
INSERT INTO menuitem_modifiergroup VALUES (7, 0, 2, 0, 2, 26);


--
-- TOC entry 3520 (class 0 OID 0)
-- Dependencies: 252
-- Name: menuitem_modifiergroup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('menuitem_modifiergroup_id_seq', 9, true);


--
-- TOC entry 3414 (class 0 OID 23662)
-- Dependencies: 285
-- Data for Name: pizza_crust; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pizza_crust VALUES (1, 'PAN', 'PAN', NULL, 0, false);
INSERT INTO pizza_crust VALUES (2, 'HAND TOSSED', 'HAND TOSSED', NULL, 1, false);


--
-- TOC entry 3418 (class 0 OID 23678)
-- Dependencies: 289
-- Data for Name: pizza_price; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3383 (class 0 OID 23533)
-- Dependencies: 254
-- Data for Name: menuitem_pizzapirce; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3385 (class 0 OID 23538)
-- Dependencies: 256
-- Data for Name: menuitem_shift; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3521 (class 0 OID 0)
-- Dependencies: 255
-- Name: menuitem_shift_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('menuitem_shift_id_seq', 1, false);


--
-- TOC entry 3416 (class 0 OID 23670)
-- Dependencies: 287
-- Data for Name: pizza_modifier_price; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3386 (class 0 OID 23544)
-- Dependencies: 257
-- Data for Name: menumodifier_pizzamodifierprice; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3404 (class 0 OID 23619)
-- Dependencies: 275
-- Data for Name: multiplier; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO multiplier VALUES ('Regular', '', 0, 0, true, true, NULL, NULL);
INSERT INTO multiplier VALUES ('No', 'No', 0, 1, false, false, NULL, NULL);
INSERT INTO multiplier VALUES ('Half', 'Half', 50, 2, false, false, NULL, NULL);
INSERT INTO multiplier VALUES ('Quarter', 'Quarter', 25, 3, false, false, NULL, NULL);
INSERT INTO multiplier VALUES ('Extra', 'Extra', 200, 4, false, false, NULL, NULL);
INSERT INTO multiplier VALUES ('Triple', 'Triple', 300, 5, false, false, NULL, NULL);
INSERT INTO multiplier VALUES ('Sub', 'Sub', 100, 6, false, false, NULL, NULL);


--
-- TOC entry 3403 (class 0 OID 23613)
-- Dependencies: 274
-- Data for Name: modifier_multiplier_price; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3522 (class 0 OID 0)
-- Dependencies: 273
-- Name: modifier_multiplier_price_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('modifier_multiplier_price_id_seq', 1, false);


--
-- TOC entry 3523 (class 0 OID 0)
-- Dependencies: 276
-- Name: order_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('order_type_id_seq', 4, true);


--
-- TOC entry 3524 (class 0 OID 0)
-- Dependencies: 278
-- Name: packaging_unit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('packaging_unit_id_seq', 1, false);


--
-- TOC entry 3410 (class 0 OID 23646)
-- Dependencies: 281
-- Data for Name: payout_reasons; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3525 (class 0 OID 0)
-- Dependencies: 280
-- Name: payout_reasons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('payout_reasons_id_seq', 1, false);


--
-- TOC entry 3412 (class 0 OID 23654)
-- Dependencies: 283
-- Data for Name: payout_recepients; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3526 (class 0 OID 0)
-- Dependencies: 282
-- Name: payout_recepients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('payout_recepients_id_seq', 1, false);


--
-- TOC entry 3527 (class 0 OID 0)
-- Dependencies: 284
-- Name: pizza_crust_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pizza_crust_id_seq', 2, true);


--
-- TOC entry 3528 (class 0 OID 0)
-- Dependencies: 286
-- Name: pizza_modifier_price_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pizza_modifier_price_id_seq', 1, false);


--
-- TOC entry 3529 (class 0 OID 0)
-- Dependencies: 288
-- Name: pizza_price_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pizza_price_id_seq', 1, false);


--
-- TOC entry 3419 (class 0 OID 23684)
-- Dependencies: 290
-- Data for Name: printer_configuration; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3530 (class 0 OID 0)
-- Dependencies: 291
-- Name: printer_group_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('printer_group_id_seq', 1, false);


--
-- TOC entry 3422 (class 0 OID 23702)
-- Dependencies: 293
-- Data for Name: printer_group_printers; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3531 (class 0 OID 0)
-- Dependencies: 294
-- Name: purchase_order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('purchase_order_id_seq', 1, false);


--
-- TOC entry 3426 (class 0 OID 23715)
-- Dependencies: 297
-- Data for Name: recepie; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3532 (class 0 OID 0)
-- Dependencies: 296
-- Name: recepie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('recepie_id_seq', 1, false);


--
-- TOC entry 3428 (class 0 OID 23723)
-- Dependencies: 299
-- Data for Name: recepie_item; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3533 (class 0 OID 0)
-- Dependencies: 298
-- Name: recepie_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('recepie_item_id_seq', 1, false);


--
-- TOC entry 3429 (class 0 OID 23729)
-- Dependencies: 300
-- Data for Name: restaurant; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO restaurant VALUES (1, 1311762252, 'Sample Restaurant', 'Somewhere', NULL, NULL, NULL, '+0123456789', 0, 0, 'Sample Currency', '$', 0, 0, NULL, false, false);


--
-- TOC entry 3534 (class 0 OID 0)
-- Dependencies: 301
-- Name: shift_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('shift_id_seq', 1, true);


--
-- TOC entry 3433 (class 0 OID 23746)
-- Dependencies: 304
-- Data for Name: shop_floor; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3535 (class 0 OID 0)
-- Dependencies: 303
-- Name: shop_floor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('shop_floor_id_seq', 1, false);


--
-- TOC entry 3435 (class 0 OID 23754)
-- Dependencies: 306
-- Data for Name: shop_floor_template; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3536 (class 0 OID 0)
-- Dependencies: 305
-- Name: shop_floor_template_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('shop_floor_template_id_seq', 1, false);


--
-- TOC entry 3436 (class 0 OID 23760)
-- Dependencies: 307
-- Data for Name: shop_floor_template_properties; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3437 (class 0 OID 23765)
-- Dependencies: 308
-- Data for Name: shop_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO shop_table VALUES (1, NULL, NULL, 4, 0, 0, false, false, false, false, false, NULL);
INSERT INTO shop_table VALUES (2, NULL, NULL, 4, 0, 0, false, false, false, false, false, NULL);
INSERT INTO shop_table VALUES (3, NULL, NULL, 4, 0, 0, false, false, false, false, false, NULL);
INSERT INTO shop_table VALUES (4, NULL, NULL, 4, 0, 0, false, false, false, false, false, NULL);
INSERT INTO shop_table VALUES (5, NULL, NULL, 4, 0, 0, false, false, false, false, false, NULL);
INSERT INTO shop_table VALUES (6, NULL, NULL, 4, 0, 0, false, false, false, false, false, NULL);
INSERT INTO shop_table VALUES (7, NULL, NULL, 4, 0, 0, false, false, false, false, false, NULL);
INSERT INTO shop_table VALUES (8, NULL, NULL, 4, 0, 0, false, false, false, false, false, NULL);
INSERT INTO shop_table VALUES (9, NULL, NULL, 4, 0, 0, false, false, false, false, false, NULL);
INSERT INTO shop_table VALUES (10, NULL, NULL, 4, 0, 0, false, false, false, false, false, NULL);
INSERT INTO shop_table VALUES (11, NULL, NULL, 4, 0, 0, false, false, false, false, false, NULL);


--
-- TOC entry 3439 (class 0 OID 23772)
-- Dependencies: 310
-- Data for Name: shop_table_type; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3537 (class 0 OID 0)
-- Dependencies: 309
-- Name: shop_table_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('shop_table_type_id_seq', 1, false);


--
-- TOC entry 3441 (class 0 OID 23780)
-- Dependencies: 312
-- Data for Name: table_booking_info; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3538 (class 0 OID 0)
-- Dependencies: 311
-- Name: table_booking_info_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('table_booking_info_id_seq', 1, false);


--
-- TOC entry 3442 (class 0 OID 23786)
-- Dependencies: 313
-- Data for Name: table_booking_mapping; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3443 (class 0 OID 23789)
-- Dependencies: 314
-- Data for Name: table_type_relation; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3539 (class 0 OID 0)
-- Dependencies: 315
-- Name: tax_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tax_id_seq', 1, true);


--
-- TOC entry 3475 (class 0 OID 23946)
-- Dependencies: 346
-- Data for Name: virtual_printer; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3448 (class 0 OID 23810)
-- Dependencies: 319
-- Data for Name: terminal_printers; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3540 (class 0 OID 0)
-- Dependencies: 318
-- Name: terminal_printers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('terminal_printers_id_seq', 1, false);


--
-- TOC entry 3450 (class 0 OID 23818)
-- Dependencies: 321
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3452 (class 0 OID 23831)
-- Dependencies: 323
-- Data for Name: ticket_discount; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3541 (class 0 OID 0)
-- Dependencies: 322
-- Name: ticket_discount_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ticket_discount_id_seq', 1, false);


--
-- TOC entry 3542 (class 0 OID 0)
-- Dependencies: 320
-- Name: ticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ticket_id_seq', 1, false);


--
-- TOC entry 3454 (class 0 OID 23839)
-- Dependencies: 325
-- Data for Name: ticket_item; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3460 (class 0 OID 23868)
-- Dependencies: 331
-- Data for Name: ticket_item_modifier; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3455 (class 0 OID 23848)
-- Dependencies: 326
-- Data for Name: ticket_item_addon_relation; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3456 (class 0 OID 23853)
-- Dependencies: 327
-- Data for Name: ticket_item_cooking_instruction; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3458 (class 0 OID 23860)
-- Dependencies: 329
-- Data for Name: ticket_item_discount; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3543 (class 0 OID 0)
-- Dependencies: 328
-- Name: ticket_item_discount_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ticket_item_discount_id_seq', 1, false);


--
-- TOC entry 3544 (class 0 OID 0)
-- Dependencies: 324
-- Name: ticket_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ticket_item_id_seq', 1, false);


--
-- TOC entry 3545 (class 0 OID 0)
-- Dependencies: 330
-- Name: ticket_item_modifier_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ticket_item_modifier_id_seq', 1, false);


--
-- TOC entry 3461 (class 0 OID 23874)
-- Dependencies: 332
-- Data for Name: ticket_item_modifier_relation; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3462 (class 0 OID 23879)
-- Dependencies: 333
-- Data for Name: ticket_properties; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3463 (class 0 OID 23887)
-- Dependencies: 334
-- Data for Name: ticket_table_num; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3465 (class 0 OID 23892)
-- Dependencies: 336
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3466 (class 0 OID 23903)
-- Dependencies: 337
-- Data for Name: transaction_properties; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3546 (class 0 OID 0)
-- Dependencies: 335
-- Name: transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('transactions_id_seq', 1, false);


--
-- TOC entry 3469 (class 0 OID 23923)
-- Dependencies: 340
-- Data for Name: user_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_permission VALUES ('Reopen Ticket');
INSERT INTO user_permission VALUES ('All Functions');
INSERT INTO user_permission VALUES ('Drawer Pull');
INSERT INTO user_permission VALUES ('Quick Maintenance');
INSERT INTO user_permission VALUES ('Perform Administrative Task');
INSERT INTO user_permission VALUES ('Split Ticket');
INSERT INTO user_permission VALUES ('Hold Ticket');
INSERT INTO user_permission VALUES ('Authorize Tickets');
INSERT INTO user_permission VALUES ('View All Close Tickets');
INSERT INTO user_permission VALUES ('Void Ticket');
INSERT INTO user_permission VALUES ('Shut Down');
INSERT INTO user_permission VALUES ('Refund');
INSERT INTO user_permission VALUES ('Add Discount');
INSERT INTO user_permission VALUES ('Booking');
INSERT INTO user_permission VALUES ('Modify Printed Ticket');
INSERT INTO user_permission VALUES ('View Explorers');
INSERT INTO user_permission VALUES ('View Reports');
INSERT INTO user_permission VALUES ('Create New Ticket');
INSERT INTO user_permission VALUES ('Pay Out');
INSERT INTO user_permission VALUES ('View All Open Ticket');
INSERT INTO user_permission VALUES ('Kitchen Display');
INSERT INTO user_permission VALUES ('Settle Ticket');
INSERT INTO user_permission VALUES ('Drawer Assignment');
INSERT INTO user_permission VALUES ('Transfer Ticket');
INSERT INTO user_permission VALUES ('Perform Manager Task');
INSERT INTO user_permission VALUES ('View Back Office');
INSERT INTO user_permission VALUES ('Manage Table Layout');


--
-- TOC entry 3547 (class 0 OID 0)
-- Dependencies: 341
-- Name: user_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_type_id_seq', 4, true);


--
-- TOC entry 3472 (class 0 OID 23936)
-- Dependencies: 343
-- Data for Name: user_user_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_user_permission VALUES (1, 'Reopen Ticket');
INSERT INTO user_user_permission VALUES (1, 'All Functions');
INSERT INTO user_user_permission VALUES (1, 'Drawer Pull');
INSERT INTO user_user_permission VALUES (1, 'Quick Maintenance');
INSERT INTO user_user_permission VALUES (1, 'Perform Administrative Task');
INSERT INTO user_user_permission VALUES (1, 'Split Ticket');
INSERT INTO user_user_permission VALUES (1, 'Hold Ticket');
INSERT INTO user_user_permission VALUES (1, 'Authorize Tickets');
INSERT INTO user_user_permission VALUES (1, 'View All Close Tickets');
INSERT INTO user_user_permission VALUES (1, 'Void Ticket');
INSERT INTO user_user_permission VALUES (1, 'Shut Down');
INSERT INTO user_user_permission VALUES (1, 'Refund');
INSERT INTO user_user_permission VALUES (1, 'Add Discount');
INSERT INTO user_user_permission VALUES (1, 'Booking');
INSERT INTO user_user_permission VALUES (1, 'Modify Printed Ticket');
INSERT INTO user_user_permission VALUES (1, 'View Explorers');
INSERT INTO user_user_permission VALUES (1, 'View Reports');
INSERT INTO user_user_permission VALUES (1, 'Create New Ticket');
INSERT INTO user_user_permission VALUES (1, 'Pay Out');
INSERT INTO user_user_permission VALUES (1, 'View All Open Ticket');
INSERT INTO user_user_permission VALUES (1, 'Kitchen Display');
INSERT INTO user_user_permission VALUES (1, 'Settle Ticket');
INSERT INTO user_user_permission VALUES (1, 'Drawer Assignment');
INSERT INTO user_user_permission VALUES (1, 'Transfer Ticket');
INSERT INTO user_user_permission VALUES (1, 'Perform Manager Task');
INSERT INTO user_user_permission VALUES (1, 'View Back Office');
INSERT INTO user_user_permission VALUES (1, 'Manage Table Layout');
INSERT INTO user_user_permission VALUES (2, 'Reopen Ticket');
INSERT INTO user_user_permission VALUES (2, 'All Functions');
INSERT INTO user_user_permission VALUES (2, 'Drawer Pull');
INSERT INTO user_user_permission VALUES (2, 'Quick Maintenance');
INSERT INTO user_user_permission VALUES (2, 'Perform Administrative Task');
INSERT INTO user_user_permission VALUES (2, 'Split Ticket');
INSERT INTO user_user_permission VALUES (2, 'Hold Ticket');
INSERT INTO user_user_permission VALUES (2, 'Authorize Tickets');
INSERT INTO user_user_permission VALUES (2, 'View All Close Tickets');
INSERT INTO user_user_permission VALUES (2, 'Void Ticket');
INSERT INTO user_user_permission VALUES (2, 'Shut Down');
INSERT INTO user_user_permission VALUES (2, 'Refund');
INSERT INTO user_user_permission VALUES (2, 'Add Discount');
INSERT INTO user_user_permission VALUES (2, 'Booking');
INSERT INTO user_user_permission VALUES (2, 'Modify Printed Ticket');
INSERT INTO user_user_permission VALUES (2, 'View Explorers');
INSERT INTO user_user_permission VALUES (2, 'View Reports');
INSERT INTO user_user_permission VALUES (2, 'Create New Ticket');
INSERT INTO user_user_permission VALUES (2, 'Pay Out');
INSERT INTO user_user_permission VALUES (2, 'View All Open Ticket');
INSERT INTO user_user_permission VALUES (2, 'Kitchen Display');
INSERT INTO user_user_permission VALUES (2, 'Settle Ticket');
INSERT INTO user_user_permission VALUES (2, 'Drawer Assignment');
INSERT INTO user_user_permission VALUES (2, 'Transfer Ticket');
INSERT INTO user_user_permission VALUES (2, 'Perform Manager Task');
INSERT INTO user_user_permission VALUES (2, 'View Back Office');
INSERT INTO user_user_permission VALUES (2, 'Manage Table Layout');
INSERT INTO user_user_permission VALUES (3, 'Create New Ticket');
INSERT INTO user_user_permission VALUES (3, 'View All Open Ticket');
INSERT INTO user_user_permission VALUES (3, 'Settle Ticket');
INSERT INTO user_user_permission VALUES (3, 'Split Ticket');
INSERT INTO user_user_permission VALUES (4, 'Create New Ticket');
INSERT INTO user_user_permission VALUES (4, 'Settle Ticket');
INSERT INTO user_user_permission VALUES (4, 'Split Ticket');


--
-- TOC entry 3548 (class 0 OID 0)
-- Dependencies: 338
-- Name: users_auto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_auto_id_seq', 5, true);


--
-- TOC entry 3549 (class 0 OID 0)
-- Dependencies: 345
-- Name: virtual_printer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('virtual_printer_id_seq', 1, false);


--
-- TOC entry 3473 (class 0 OID 23941)
-- Dependencies: 344
-- Data for Name: virtualprinter_order_type; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3477 (class 0 OID 23956)
-- Dependencies: 348
-- Data for Name: void_reasons; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3550 (class 0 OID 0)
-- Dependencies: 347
-- Name: void_reasons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('void_reasons_id_seq', 1, false);


--
-- TOC entry 3479 (class 0 OID 23964)
-- Dependencies: 350
-- Data for Name: zip_code_vs_delivery_charge; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3551 (class 0 OID 0)
-- Dependencies: 349
-- Name: zip_code_vs_delivery_charge_auto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('zip_code_vs_delivery_charge_auto_id_seq', 1, false);


-- Completed on 2017-04-17 10:39:52 ICT

--
-- PostgreSQL database dump complete
--

