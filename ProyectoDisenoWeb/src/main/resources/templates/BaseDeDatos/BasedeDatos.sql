drop schema if exists FAEJ; 
drop user if exists usuario; 
CREATE SCHEMA FAEJ;

create user 'usuario' identified by 'clave';
grant all privileges on FAEJ.* to 'usuario'; 
flush privileges;

CREATE TABLE IF NOT EXISTS FAEJ.categoria (
	id_categoria INT AUTO_INCREMENT NOT NULL, 
    nombre VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_categoria)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS FAEJ.estilo (
	id_estilo INT AUTO_INCREMENT NOT NULL, 
    nombre VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_estilo)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS FAEJ.producto (
  id_producto INT AUTO_INCREMENT NOT NULL,
  id_categoria INT NULL,
  id_estilo INT NULL,
  nombre VARCHAR(255) NOT NULL,
  marca VARCHAR(255) NOT NULL,
  descripcion TEXT NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  cantidad INT NOT NULL,
  status VARCHAR(50),
  ruta_imagen VARCHAR(1024) NOT NULL,
  PRIMARY KEY (id_producto),
  FOREIGN KEY fk_producto_categoria (id_categoria) REFERENCES categoria(id_categoria),
  FOREIGN KEY fk_producto_estilo (id_estilo) REFERENCES estilo(id_estilo)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS FAEJ.usuario (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  cedula VARCHAR(30) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  telefono VARCHAR(255) NOT NULL,
  direccion VARCHAR(255) NOT NULL,
  rol INT NOT NULL
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS FAEJ.pedido (
  id_pedido INT AUTO_INCREMENT PRIMARY KEY,
  id_usuario INT NOT NULL,
  fecha_pedido DATE NOT NULL,
  estado VARCHAR(255) NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  detalle_pedido TEXT NOT NULL,
  FOREIGN KEY (id_usuario) REFERENCES FAEJ.usuario(id_usuario)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO faej.usuario (id_usuario, cedula, nombre, email, password, telefono, direccion, rol) VALUES
(1, 118810955, 'Ariana', '1234@gmail.com', 1234, 4848, 'NA', 1);

INSERT INTO faej.categoria (id_categoria,nombre) VALUES 
('1','Hombre'), 
('2','Mujer'),
('3','Niñ@');

INSERT INTO faej.estilo (id_estilo,nombre) VALUES 
('1','Lifestyle'), 
('2','Running'),
('3','Trail Running'),
('4','Training & Gym'),
('5','Sportswear'),
('6','Skateboarding');

INSERT INTO FAEJ.producto
(id_producto, id_categoria, id_estilo, nombre, marca, descripcion, precio, cantidad, status, ruta_imagen) 
VALUES  
(1, 2, 1, 'Nike Dunk Low Retro', 'Nike','Calzado para Hombre', 105.00, 100, 'Activo', 'https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.5/h_316,c_limit/eba7a509-73b5-473f-a964-6bea77d8ebf1/calzado-dunk-low-retro-76KnBL.png'),
(2, 2, 3, 'Sabrina 1 "BKLYN" Finest',  'Adidas','Calzado de Basquetbol', 130.00, 50,'Activo', 'https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.5/h_316,c_limit/ec6ac4fe-7638-4a64-a7a0-6b9d045af711/calzad-de-b%C3%A1squetbol-sabrina-1-bklyns-finest-f8jr2H.png'),
(3, 1, 4, 'Nike Dunk Low', 'Nike', 'Calzado para Mujer', 130.00, 100,'Activo','https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.5/h_316,c_limit/ac89cfe2-a425-41b4-b51a-facde9df1215/calzado-dunk-low-KxSmbV.png'),
(4, 3, 6, 'Cuck Taylor All Star EVA Lift Platform Y2K Heart', 'Converse',  'Niño Zapatillas High Top', 96.00, 50,'Activo',  'https://www.converse.com/dw/image/v2/BJJF_PRD/on/demandware.static/-/Sites-cnv-master-catalog-we/default/dw70326ae6/images/a_08/A09121C_A_08X1.jpg'),
(5, 3, 5, 'Chuck Taylor All Star Ultra Retro','Converse',  'Pequeño Zapatillas Mid Top',  89.00, 50,'Activo', 'https://www.converse.com/dw/image/v2/BJJF_PRD/on/demandware.static/-/Sites-cnv-master-catalog-we/default/dwe3c4cdcf/images/a_08/A06376C_A_08X1.jpg');

CREATE TABLE IF NOT EXISTS FAEJ.rol (
	id_rol INT NOT NULL AUTO_INCREMENT,
    nombre varchar(255),
    id_usuario INT NOT NULL,
    PRIMARY KEY(id_rol),
    FOREIGN KEY fk_rol_usuario (id_usuario) REFERENCES usuario(id_usuario)
)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;