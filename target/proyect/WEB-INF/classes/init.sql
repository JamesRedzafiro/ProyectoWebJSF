-- Script SQL para crear tablas en la base de datos spa_relax
-- Nota: La BD debe estar creada previamente

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    dni VARCHAR(20),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de servicios
CREATE TABLE IF NOT EXISTS servicios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    duracion INTEGER NOT NULL
);

-- Tabla de citas
CREATE TABLE IF NOT EXISTS citas (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    servicio_id INTEGER NOT NULL REFERENCES servicios(id) ON DELETE CASCADE,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado VARCHAR(50) DEFAULT 'confirmada',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear índices
CREATE INDEX IF NOT EXISTS idx_usuario_email ON usuarios(email);
CREATE INDEX IF NOT EXISTS idx_citas_usuario ON citas(usuario_id);
CREATE INDEX IF NOT EXISTS idx_citas_servicio ON citas(servicio_id);
CREATE INDEX IF NOT EXISTS idx_citas_fecha ON citas(fecha);

-- Limpiar datos anteriores
DELETE FROM citas;
DELETE FROM servicios;
DELETE FROM usuarios;

-- Resetear secuencias
ALTER SEQUENCE usuarios_id_seq RESTART WITH 1;
ALTER SEQUENCE servicios_id_seq RESTART WITH 1;
ALTER SEQUENCE citas_id_seq RESTART WITH 1;

-- Insertar usuarios de prueba
INSERT INTO usuarios (nombre, dni, email, password, telefono) VALUES
('Juan García', '12345678', 'juan@email.com', '123456', '987654321'),
('María López', '87654321', 'maria@email.com', 'password123', '987654322'),
('Carlos Rodríguez', '11223344', 'carlos@email.com', 'abc123', '987654323'),
('Admin Spa', '99887766', 'admin@spa.com', 'admin123', '987654324');

-- Insertar servicios
INSERT INTO servicios (nombre, descripcion, precio, duracion) VALUES
('Masaje Relajante', 'Masaje completo de cuerpo relajante', 50.00, 60),
('Masaje Terapéutico', 'Masaje para aliviar tensiones musculares', 60.00, 75),
('Masaje Facial', 'Limpieza y masaje facial rejuvenecedor', 35.00, 45),
('Reflexología', 'Masaje de pies estimulante', 40.00, 60),
('Masaje Hot Stone', 'Masaje con piedras calientes', 75.00, 90),
('Spa Completo', 'Paquete completo: masaje + facial + baño', 120.00, 180),
('Masaje Tailandés', 'Masaje tradicional tailandés', 65.00, 90),
('Drenaje Linfático', 'Masaje para mejorar circulación', 55.00, 60),
('Aromaterapia', 'Masaje con aceites aromáticos', 50.00, 60),
('Masaje Descontracturante', 'Masaje especializado para contracturas', 70.00, 75);

-- Insertar citas de prueba
INSERT INTO citas (usuario_id, servicio_id, fecha, hora, estado) VALUES
(1, 1, '2026-04-20', '10:00', 'confirmada'),
(1, 2, '2026-04-21', '14:30', 'confirmada'),
(1, 3, '2026-04-22', '15:00', 'confirmada'),
(2, 1, '2026-04-20', '11:00', 'confirmada'),
(2, 5, '2026-04-23', '09:00', 'confirmada'),
(2, 6, '2026-04-25', '16:00', 'confirmada'),
(3, 2, '2026-04-19', '12:00', 'completada'),
(3, 4, '2026-04-20', '13:30', 'confirmada'),
(3, 7, '2026-04-21', '10:30', 'confirmada'),
(1, 8, '2026-04-26', '11:00', 'confirmada'),
(2, 9, '2026-04-27', '15:00', 'confirmada'),
(3, 10, '2026-04-28', '10:00', 'confirmada');

