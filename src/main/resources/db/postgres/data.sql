INSERT INTO users VALUES (1, 'jackobrien', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_ADMIN',
'Jack', 'O’Brien', 'Information Technology', 'Specialist', '+353 1 437 0969', 'jackobrien@amics.ie') ON CONFLICT (id) DO NOTHING;

INSERT INTO users VALUES (2, 'gracecarrol', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_MASTER',
'Grace', 'Carroll', 'Purchasing', 'Specialist', '+353 1 889 7856', 'gracecarrol@amics.ie') ON CONFLICT (id) DO NOTHING;

INSERT INTO users VALUES (3, 'danielcollins', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Daniel', 'Collins', 'Plant Engineering', 'Coordinator', '+353 1 645 0897', 'danielcollins@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (4, 'conorfarrel', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Conor', 'Farrell', 'Plant Engineering', 'Senior Engineer', '+353 1 476 0321', 'conorfarrel@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (5, 'noahoconnor', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Noah', 'O’Connor', 'Plant Engineering', 'Engineer', '+353 1 668 7571', 'noahoconnor@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (6, 'finnmartin', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Finn', 'Martin', 'Plant Engineering', 'Engineer', '+353 1 429 0892', 'finnmartin@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (7, 'hannahcampbell', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Hannah', 'Campbell', 'Plant Engineering', 'Specialist', '+353 1 072 5491', 'hannahcampbell@amics.ie') ON CONFLICT (id) DO NOTHING;

INSERT INTO users VALUES (8, 'alexodonnell', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Alex', 'O’Donnell', 'Industrial Safety', 'Senior Engineer', '+353 1 198 0935', 'alexodonnell@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (9, 'ryanoneill', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Ryan', 'O’Neill', 'Industrial Safety', 'Engineer', '+353 1 245 0966', 'ryanoneill@amics.ie') ON CONFLICT (id) DO NOTHING;

INSERT INTO users VALUES (10, 'gaelmccarthy', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Gael', 'McCarthy', 'Maintenance', 'Coordinator', '+353 1 715 0429', 'gaelmccarthy@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (11, 'roryhayes', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Rory', 'Hayes', 'Maintenance', 'Senior Engineer', '+353 1 663 0713', 'roryhayes@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (12, 'lorcanfitzgerald', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Lorcan', 'Fitzgerald', 'Maintenance', 'Engineer', '+353 1 769 2098', 'lorcanfitzgerald@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (13, 'quilloreilly', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Quill', 'O’Reilly', 'Maintenance', 'Engineer', '+353 1 822 0141', 'quilloreilly@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (14, 'chloeclarke', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Chloe', 'Clarke', 'Maintenance', 'Specialist', '+353 1 159 0386', 'chloeclarke@amics.ie') ON CONFLICT (id) DO NOTHING;

INSERT INTO users VALUES (15, 'briendoyle', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Brien', 'Doyle', 'Production Management', 'Coordinator', '+353 1 031 0922', 'briendoyle@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (16, 'torinkennedy', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Torin', 'Kennedy', 'Production Management', 'Senior Engineer', '+353 1 525 0206', 'torinkennedy@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (17, 'pauricgallagher', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Pauric', 'Gallagher', 'Production Management', 'Engineer', '+353 1 221 5533', 'pauricgallagher@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (18, 'saeranwilson', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Saeran', 'Wilson', 'Production Management', 'Engineer', '+353 1 381 0905', 'saeranwilson@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (19, 'kateburke', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Kate', 'Burke', 'Production Management', 'Specialist', '+353 1 306 8741', 'kateburke@amics.ie') ON CONFLICT (id) DO NOTHING;

INSERT INTO users VALUES (20, 'charliejohnston', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Charlie', 'Johnston', 'Quality Control', 'Coordinator', '+353 1 190 1422', 'charliejohnston@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (21, 'liambrown', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Liam', 'Brown', 'Quality Control', 'Senior Engineer', '+353 1 007 2035', 'liambrown@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (22, 'adamflynn', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Adam', 'Flynn', 'Quality Control', 'Engineer', '+353 1 013 0906', 'adamflynn@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (23, 'lukehealy', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Luke', 'Healy', 'Quality Control', 'Engineer', '+353 1 715 2237', 'lukehealy@amics.ie') ON CONFLICT (id) DO NOTHING;
INSERT INTO users VALUES (24, 'haileyregan', '$2a$10$H.W3HhF8pd2Y/TLfDM.t2uQ0F3yI9el9F8nio6VUijbq.SAg/5Ocq', 1, 'ROLE_USER',
'Hailey', 'Regan', 'Quality Control', 'Specialist', '+353 1 555 0199', 'haileyregan@amics.ie') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (1, '67h95110e543', 'Heating', '2022-01-20T20:00:31.650521+03:00', 4,
'CCR1-240160-100', 'Corrosion-resistant washdown unit heater', 'CCR1-240160-100', 'CCR1.png', 'Caloritech', 'United States',
'620 mm', '325 mm', '1800 mm', '34.1 kg', 'https://content.thermon.com/pdf/Caloritech/Catalog/CCR1_Catalog.pdf') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (2, '39h43745e361', 'Electrical', '2022-05-15T15:36:05.436774+03', 12,
'1FT7084-5AC71-1FB1', 'Synchronous motor', 'SIMOTICS S 1FT7', '1FT7084-5AC71-1FB1.png', 'Siemens', 'Germany',
'', '', '', '24 kg', 'M0=20Nm (100K) NN=2000rpm, PN=3.54kW Naturally cooled Frame size IMB 5 (IM V1, IM V3) Conventional flange (1FT6/1FK7 compatible)
POWER CONNECTOR ROTATABLE ABSOLUTE ENCODER 22 BITS MULTI TURN WITH DRIVE-CLIQ INTERFACE (ENCODER AM-22-DQ) CONNECTOR ROTATABLE SHAFT WITH FITTED KEY;
WITH HOLDING BRAKE; ACC. TOLERANCE N VIBRATION SEVERITY GRADE A, DEGREE OF PROTECTION IP 65') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (3, '09h70728e593', 'Electronics', '2022-11-04T15:49:22.565189+03', 22,
'6ES7155-6AR00-0AN0', 'PROFINET interface module', 'SIMATIC ET 200SP IM 155-6 PN BA', '6ES7155-6AR00-0AN0.png', 'Siemens', 'Germany',
'74 mm', '35 mm', '117 mm', '125 g', 'Max. 12 I/O modules, 2x integrated RJ45 sockets incl. server module') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (4, '29h64868e615', 'Electronics', '2022-08-12T15:54:56.528053+03', 13,
'6ES7155-6AU01-0BN0', 'PROFINET interface module', 'SIMATIC ET 200SP M 155-6 PN ST', '6ES7155-6AU01-0BN0.png', 'Siemens', 'Germany',
'74 mm', '50 mm', '117 mm', '147 g', 'Max. 32 I/O modules, and 16 ET 200AL modules, single hot swap, incl. server module (6ES7193-6PA00-0AA0)')
ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (5, '76h83496e201', 'Electrical', '2022-07-18T10:03:22.43271+03', 5,
'GV2ME01', 'Motor circuit breaker', 'TeSys Deca GV2', 'GV2ME01.png', 'Schneider Electric', 'France',
'78.5 mm', '45 mm', '89 mm', '0.26 kg', 'Poles description: 3P; network type: AC; [In] rated current: 0.16 A;
thermal protection adjustment range 0.1…0.16 A; magnetic tripping current: 1.5 A') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (6, '44h88767e534', 'Electrical', '2022-04-03T11:21:32.71199+03', 5,
'LRD08', 'Differential thermal overload relay', 'TeSys Deca LRD', 'LRD08.png', 'Schneider Electric', 'France',
'70 mm', '45 mm', '66 mm', '0.124 kg', 'Network type: AC, DC; thermal protection adjustment range: 2.5…4 A;
auxiliary contact composition: 1 NO + 1 NC') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (7, '58h23915e201', 'Electrical', '2022-02-07T15:28:47.305944+03', 17,
'LUB12', 'Non reversing power base', 'TeSys Ultra LUB', 'LUB12.png', 'Schneider Electric', 'France',
'126 mm', '45 mm', '154 mm', '0.9 kg', 'Poles description: 3P; [Ith] conventional free air thermal current: 12 A;
[Ue] rated operational voltage 690 V AC for power circuit; auxiliary contact composition: 1 NO + 1 NC') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (8, '30h52569e428', 'Electrical', '2022-02-20T13:34:50.787951+03', 13,
'TPRST009', 'Direct on line motor starter', 'TeSys island TPRST', 'TPRST009.png', 'Schneider Electric', 'France',
'115 mm', '45 mm', '116 mm', '0.656 kg', 'https://download.schneider-electric.com/files?p_enDocType=User+guide&p_File_Name=DOCA0224EN-00.pdf&p_Doc_Ref=DOCA0224EN')
ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (9, '82h16047e756', 'Electrical', '2022-03-12T14:40:20.684137+03', 8,
'R9F64320', 'Miniature circuit-breaker', 'Resi9 MCB', 'R9F64320.png', 'Schneider Electric', 'France',
'75 mm', '54 mm', '85 mm', '0.321 kg', 'Poles description: 3P; network type: AC; magnetic tripping limit: 5...10 x In') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (10, '10h75036e635', 'Ventilation', '2022-09-12T10:50:45.810303+03', 6,
'485B1-2', 'Exhaust fan in box housing', '485B1-2', '485B1-2.png', 'Schaefer', 'United States',
'', '', '', '', 'Speeds: 2: diameter (inches): 48; number of wings: 5; voltage: 230') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (11, '99h47459e398', 'Ventilation', '2022-06-23T16:08:59.493474+03', 6,
'FFM525G1-3', 'Fiberglass belt drive exhaust fan', 'FFM525G1-3', 'FFM525G1-3.png', 'Schaefer', 'United States',
'', '', '', '', 'Diameter (inches): 52; number of wings: 5; voltage: 208-230/460') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (12, '66h12772e021', 'Air conditioning', '2022-07-05T15:41:10.312948+03', 21,
'PUHZ-ZRP125VKA3R1', 'Split-type, heat pump air conditioner', 'PUHZ-ZRP125VKA3R1', 'PUHZ-ZRP.png', 'Mitsubishi Electric', 'Japan',
'330 mm', '1050 mm', '1338 mm', '116 kg', 'https://www.manualslib.com/manual/2394075/Mitsubishi-Electric-Puhz-Zrp35vka.html') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (13, '04h84995e175', 'Heating', '2022-05-22T11:20:17.336819+03', 16,
'FE2-380350-209', 'Explosion-proof electric air unit heater', 'FE2-380350-209', 'FE2.png', 'Ruffneck', 'United States',
'', '', '', '', 'https://content.thermon.com/pdf/Ruffneck/Catalog/FE2_Catalog.pdf') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (14, '56h35862e359', 'Mechanical', '2022-01-27T12:39:47.179479+03', 6,
'2LC0370-0AA', 'All-steel multi-disk coupling', 'N-ARPEX', 'Kachelbild-N-ARPEX.png', 'Flender', 'Germany',
'190 mm', '86 mm', '86 mm', '1.9 kg', 'https://www.flender.com/en/media-download/media/Flender_TorsionallyRigidCouplings') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (15, '11h41221e688', 'Mechanical', '2022-02-02T12:56:07.271534+03', 6,
'2LC0301-0AD', 'Double-jointed gear coupling', 'ZAPEX ZW', 'Kachelbild-ZAPEX-ZW.png', 'Flender', 'Germany',
'660 mm', '522 mm', '522 mm', '170 kg', 'https://www.flender.com/en/media-download/media/Flender_TorsionallyRigidCouplings') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (16, '91h40514e543', 'Mechanical', '2022-11-13T14:46:16.848784+03', 11,
'2LC0330-0AA', 'Double-jointed gear coupling', 'ZAPEX ZN', 'Kachelbild-ZAPEX-ZN.png', 'Flender', 'Germany',
'117 mm', '86 mm', '86 mm', '3.2 kg', 'https://www.flender.com/en/media-download/media/Flender_TorsionallyRigidCouplings') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (17, '48h65573e356', 'Hydraulics', '2022-12-29T13:18:59.130218+03', 6,
'Hydraulic press P15L', 'Professional hydraulic press', 'P15L', 'P15L.png', 'Probike', 'United Kingdom',
'', '750 mm', '1300 mm', '110 kg', 'Full range from 12 to 100 tonnes. 2 Speed manual pump with safety overload valve. Precision built ram with internal spring action.
Accurate 4" pressure gauge. Pair of vee/parallel blocks included. 30 - 100 tonne models have winch operated table. Over stroking valve to prevent cylinder damage.
Fully welded construction.') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (18, '56h10157e430', 'Gas', '2022-05-26T15:25:36.882354+03', 7,
'RC1BOX10', 'Oxygen regulator', 'RC1BOX10', 'RC1BOX10.png', 'Tesuco', 'Denmark', '', '', '', '', 
'Regulator 1 Stage Bottom Entry; Oxygen Type 10; In 20,000 kPa; Out 1,000 kPa') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (19, '83h39851e755', 'Gas', '2022-04-21T16:37:59.411068+03', 9,
'RCPRFG', 'Pipeline regulator', 'RCPRFG', 'RCPRFG.png', 'Tesuco', 'Denmark', '', '', '', '', 
'Acetylene Rear Entry 1/4 NPT; In 2,100 kPa; Out 150 kPa') ON CONFLICT (id) DO NOTHING;

INSERT INTO inventory_cards VALUES (20, '88h27958e046', 'Gas', '2022-09-11T13:40:35.275607+03', 7,
'SF0801-4420', 'Gas mixer', 'SF0801-4420', 'SF0801-4420.png', 'Tesuco', 'Denmark', '', '', '', '', 
'Argon / Helium Gas Mixer; In: 1/4" NPT F; Out: 1/4" NPT F') ON CONFLICT (id) DO NOTHING;