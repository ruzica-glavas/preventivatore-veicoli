CREATE DATABASE db_preventivatore;
USE db_preventivatore;

-- 2. tabella vehicles
DROP TABLE IF EXISTS vehicles;
CREATE TABLE vehicles (
  id INT AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(50) NOT NULL,
  brand VARCHAR(100) NOT NULL,
  model VARCHAR(100) NOT NULL,
  year SMALLINT NOT NULL,
  engine_capacity INT NOT NULL,
  fuel VARCHAR(50) NOT NULL,
  base_price FLOAT NOT NULL
) ENGINE=InnoDB;

-- 3. tabella customers
DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL,
  phone VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

-- 4. tabella optionals
DROP TABLE IF EXISTS optionals;
CREATE TABLE optionals (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  price FLOAT NOT NULL
) ENGINE=InnoDB;

-- 5. tabella estimates (preventivi)
DROP TABLE IF EXISTS estimates;
CREATE TABLE estimates (
  id INT AUTO_INCREMENT PRIMARY KEY,
  final_price FLOAT NOT NULL,
  created_at DATE NOT NULL,
  notes TEXT,
  vehicle_id INT NOT NULL,
  customer_id INT NOT NULL,
  FOREIGN KEY (vehicle_id) REFERENCES vehicles(id),
  FOREIGN KEY (customer_id) REFERENCES customers(id)
) ENGINE=InnoDB;

-- 6. tabella ponte estimate_optional
DROP TABLE IF EXISTS estimate_optional;
CREATE TABLE estimate_optional (
  estimate_id INT NOT NULL,
  optional_id INT NOT NULL,
  PRIMARY KEY (estimate_id, optional_id),
  FOREIGN KEY (estimate_id) REFERENCES estimates(id),
  FOREIGN KEY (optional_id) REFERENCES optionals(id)
) ENGINE=InnoDB;

-- ================================
-- VEICOLI (~15)
-- ================================
INSERT INTO vehicles (type, brand, model, year, engine_capacity, fuel, base_price) VALUES
('auto', 'Fiat', 'Panda', 2023, 1200, 'benzina', 12000.0),
('auto', 'Dacia', 'Duster', 2024, 1500, 'diesel', 17000.0),
('moto', 'Yamaha', 'MT-07', 2022, 689, 'benzina', 7500.0),
('furgone', 'Ford', 'Transit', 2024, 2000, 'diesel', 25000.0),
('auto', 'Volkswagen', 'Golf', 2023, 1400, 'ibrido', 23000.0),
('auto', 'Toyota', 'Yaris', 2024, 1000, 'benzina', 16000.0),
('auto', 'Renault', 'Clio', 2023, 1200, 'diesel', 15000.0),
('moto', 'Honda', 'CBR500R', 2023, 471, 'benzina', 6500.0),
('auto', 'Peugeot', '208', 2024, 1300, 'ibrido', 17000.0),
('auto', 'Ford', 'Fiesta', 2023, 1200, 'benzina', 14500.0),
('furgone', 'Mercedes', 'Sprinter', 2024, 2200, 'diesel', 30000.0),
('auto', 'Audi', 'A3', 2023, 1400, 'benzina', 28000.0),
('moto', 'KTM', 'Duke 390', 2023, 373, 'benzina', 5500.0),
('auto', 'Opel', 'Corsa', 2024, 1300, 'benzina', 15500.0),
('auto', 'Hyundai', 'i20', 2023, 1200, 'benzina', 14800.0);

-- ================================
-- CLIENTI (~15)
-- ================================
INSERT INTO customers (first_name, last_name, email, phone) VALUES
('Mario', 'Rossi', 'mario.rossi@example.com', '333-1234567'),
('Luigi', 'Bianchi', 'luigi.bianchi@example.com', '333-7654321'),
('Giulia', 'Verdi', 'giulia.verdi@example.com', '333-1112233'),
('Francesco', 'Neri', 'francesco.neri@example.com', '333-4455667'),
('Anna', 'Gialli', 'anna.gialli@example.com', '333-7788990'),
('Paolo', 'Blu', 'paolo.blu@example.com', '333-1122334'),
('Chiara', 'Rosa', 'chiara.rosa@example.com', '333-2233445'),
('Alessandro', 'Marrone', 'alessandro.marrone@example.com', '333-3344556'),
('Martina', 'Viola', 'martina.viola@example.com', '333-4455667'),
('Davide', 'Grigio', 'davide.grigio@example.com', '333-5566778'),
('Elena', 'Arancio', 'elena.arancio@example.com', '333-6677889'),
('Lorenzo', 'Celeste', 'lorenzo.celeste@example.com', '333-7788991'),
('Federica', 'Lilla', 'federica.lilla@example.com', '333-8899001'),
('Riccardo', 'Nocciola', 'riccardo.nocciola@example.com', '333-9900112'),
('Sofia', 'Smeraldo', 'sofia.smeraldo@example.com', '333-1011121');

-- ================================
-- OPTIONAL
-- ================================
INSERT INTO optionals (name, price) VALUES
('Climatizzatore', 800.0),
('Navigatore', 600.0),
('Sensori di parcheggio', 400.0),
('Tettuccio panoramico', 1200.0),
('Cerchi in lega', 700.0),
('Sedili riscaldati', 500.0),
('Telecamera posteriore', 300.0),
('Sistema audio premium', 900.0);

-- ================================
-- ESEMPI DI PREVENTIVI
-- ================================
-- Preventivo 1: cliente 1, veicolo 1, climatizzatore + navigatore
INSERT INTO estimates (final_price, created_at, notes, vehicle_id, customer_id)
VALUES (13000.0, '2025-11-26', 'Preventivo con climatizzatore e navigatore', 1, 1);
INSERT INTO estimate_optional (estimate_id, optional_id) VALUES (1, 1), (1, 2);

-- Preventivo 2: cliente 2, veicolo 2, sensori di parcheggio
INSERT INTO estimates (final_price, created_at, notes, vehicle_id, customer_id)
VALUES (17400.0, '2025-11-25', 'Preventivo con sensori di parcheggio', 2, 2);
INSERT INTO estimate_optional (estimate_id, optional_id) VALUES (2, 3);

-- Preventivo 3: cliente 3, veicolo 3, sedili riscaldati + telecamera posteriore
INSERT INTO estimates (final_price, created_at, notes, vehicle_id, customer_id)
VALUES (7400.0, '2025-11-24', 'Moto con sedili riscaldati e telecamera', 3, 3);
INSERT INTO estimate_optional (estimate_id, optional_id) VALUES (3, 6), (3, 7);

-- Preventivo 4: cliente 4, veicolo 4, climatizzatore + tettuccio panoramico
INSERT INTO estimates (final_price, created_at, notes, vehicle_id, customer_id)
VALUES (27000.0, '2025-11-23', 'Furgone con climatizzatore e tettuccio panoramico', 4, 4);
INSERT INTO estimate_optional (estimate_id, optional_id) VALUES (4, 1), (4, 4);

-- Preventivo 5: cliente 5, veicolo 5, cerchi in lega + sistema audio premium
INSERT INTO estimates (final_price, created_at, notes, vehicle_id, customer_id)
VALUES (25000.0, '2025-11-22', 'Auto con cerchi in lega e audio premium', 5, 5);
INSERT INTO estimate_optional (estimate_id, optional_id) VALUES (5, 5), (5, 8);

-- Aggiungi qualche altro preventivo per test vari...
INSERT INTO estimates (final_price, created_at, notes, vehicle_id, customer_id)
VALUES 
(16000.0, '2025-11-21', 'Preventivo base per Toyota Yaris', 6, 6),
(15500.0, '2025-11-20', 'Renault Clio con optional base', 7, 7),
(6700.0, '2025-11-19', 'Honda CBR500R con optional base', 8, 8);

-- Associa optional ai preventivi extra
INSERT INTO estimate_optional (estimate_id, optional_id) VALUES
(6, 1), (6, 2), (7, 3), (8, 6);