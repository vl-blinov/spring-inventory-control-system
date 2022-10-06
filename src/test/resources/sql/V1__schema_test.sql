CREATE TABLE IF NOT EXISTS users (
	id 		   SERIAL PRIMARY KEY,
	username   VARCHAR(30) NOT NULL,
	password   VARCHAR(70),
	enabled    SMALLINT NOT NULL,
	authority  VARCHAR(30) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name  VARCHAR(50) NOT NULL,
	department VARCHAR(70) NOT NULL,
	position   VARCHAR(70) NOT NULL,
	phone 	   VARCHAR(25) NOT NULL,
	email 	   VARCHAR(70) NOT NULL
);

CREATE TABLE IF NOT EXISTS inventory_cards (
	id 					 SERIAL PRIMARY KEY,
	identifier 			 VARCHAR(12) UNIQUE NOT NULL,
	class 				 VARCHAR(200) NOT NULL,
	created_at 			 TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	user_id 			 INTEGER REFERENCES users(id),
	product_id 			 VARCHAR(70) NOT NULL,
	product_name 		 VARCHAR(70) NOT NULL,
	product_type 		 VARCHAR(70) NOT NULL,
	product_image 		 TEXT,
	product_manufacturer VARCHAR(70) NOT NULL,
	product_country 	 VARCHAR(70) NOT NULL,
	product_length 		 VARCHAR(10),
	product_width 		 VARCHAR(10),
	product_height 		 VARCHAR(10),
	product_weight 		 VARCHAR(10),
	product_description  VARCHAR(500)
);