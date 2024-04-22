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
 existencias INT NOT NULL,
  status VARCHAR(50),
  ruta_imagen VARCHAR(1024) NOT NULL,
  PRIMARY KEY (id_producto),
  FOREIGN KEY fk_producto_categoria (id_categoria) REFERENCES categoria(id_categoria),
  FOREIGN KEY fk_producto_estilo (id_estilo) REFERENCES estilo(id_estilo)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS FAEJ.usuario (
  id_usuario INT AUTO_INCREMENT PRIMARY KEY,
  cedula VARCHAR(30) NOT NULL,
  username VARCHAR(255) NOT NULL,
  apellidos VARCHAR(255) NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(1024) NOT NULL,
  telefono VARCHAR(255) NOT NULL,
  direccion VARCHAR(255) NOT NULL,
  ruta_imagen varchar(1024),
  activo boolean
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;


create table faej.factura (
  id_factura INT NOT NULL AUTO_INCREMENT,
  id_usuario INT NOT NULL,
  fecha date,  
  total double,
  estado VARCHAR(255),
  direccion VARCHAR(1024),
  metodo_pago VARCHAR(100),
  ruta_imagen VARCHAR(1024),
  PRIMARY KEY (id_factura),
  foreign key fk_factura_usuario (id_usuario) references usuario(id_usuario)  
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table faej.venta (
  id_venta INT NOT NULL AUTO_INCREMENT,
  id_factura INT NOT NULL,
  id_producto INT NOT NULL,
  precio double, 
  cantidad int,
  nombre_producto VARCHAR(255),
  nombre_usuario VARCHAR(255),
  ruta_imagen VARCHAR(1024),
  PRIMARY KEY (id_venta),
  foreign key fk_ventas_factura (id_factura) references factura(id_factura),
  foreign key fk_ventas_producto (id_producto) references producto(id_producto) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE IF NOT EXISTS FAEJ.deseos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_producto INT NOT NULL,
    nombre_producto VARCHAR(255),
    descripcion VARCHAR(255),
    marca VARCHAR(50),
	ruta_imagen VARCHAR(1024),
    precio double,
    categoria VARCHAR(50),
    estilo VARCHAR(50),
    existencias int,
    foreign key fk_deseos_usuario (id_usuario) REFERENCES FAEJ.usuario(id_usuario),
     foreign key fk_ventas_factura (id_producto) REFERENCES FAEJ.producto(id_producto)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS FAEJ.rol (
	id_rol INT NOT NULL AUTO_INCREMENT,
    nombre varchar(255),
    id_usuario INT NOT NULL,
    PRIMARY KEY(id_rol),
    FOREIGN KEY fk_rol_usuario (id_usuario) REFERENCES usuario(id_usuario)
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS FAEJ.tallas (
	id_talla INT NOT NULL AUTO_INCREMENT,
    talla double,
    id_producto INT NOT NULL,
    PRIMARY KEY(id_talla),
    FOREIGN KEY fk_talla_producto (id_producto) REFERENCES producto(id_producto)
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS FAEJ.color (
	id_color INT NOT NULL AUTO_INCREMENT,
    color VARCHAR(50),
    id_producto INT NOT NULL,
    PRIMARY KEY(id_color),
    FOREIGN KEY fk_talla_color (id_color) REFERENCES producto(id_producto)
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS FAEJ.comentarios (
	id_comentario INT NOT NULL AUTO_INCREMENT,
    comentario VARCHAR(255) NOT NULL,
    talla double NOT NULL,
    color VARCHAR(50) NOT NULL,
    fecha date NOT NULL,
    usuario Varchar(255) NOT NULL,
    nombre_producto Varchar(50) NOT NULL,
    id_producto INT NOT NULL,
    id_usuario INT NOT NULL,
    PRIMARY KEY(id_comentario),
    FOREIGN KEY fk_comentario_producto (id_producto) REFERENCES producto(id_producto),
    FOREIGN KEY fk_comentario_usuario (id_usuario) REFERENCES usuario(id_usuario)
)ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO faej.usuario (id_usuario, cedula, username, email, apellidos, nombre, password, telefono, direccion, ruta_imagen, activo) VALUES
(1, 118810955, 'Ariana', '1234@gmail.com', 'Fallas Calderon',  'Ariana','$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.', 4848, 'La Cangreja, El Guarco, Cartago', 'https://th.bing.com/th/id/R.0ababdb27dd0bb71f21f03c98b6cd6f1?rik=%2fiVDxahrgNztPA&pid=ImgRaw&r=0',true),
(2, 905635, 'Fernanda', 'fer@gmail.com', 'Ureña', 'Fernanda', '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.', 4848, 'NA', 'https://th.bing.com/th/id/R.0ababdb27dd0bb71f21f03c98b6cd6f1?rik=%2fiVDxahrgNztPA&pid=ImgRaw&r=0',true),
(3, 2344, 'Jorge', 'jorge@gmail.com', 'Romero', 'Jorge', '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.',234 , 'NA', 'https://th.bing.com/th/id/R.0ababdb27dd0bb71f21f03c98b6cd6f1?rik=%2fiVDxahrgNztPA&pid=ImgRaw&r=0',true),
(4, 55555, 'Rebeca', 'rebeca@gmail.com', 'Fallas Calderon', 'Rebeca', '$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.',234 , 'Limón, Costa Rica', 'https://th.bing.com/th/id/R.0ababdb27dd0bb71f21f03c98b6cd6f1?rik=%2fiVDxahrgNztPA&pid=ImgRaw&r=0',true);
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
(id_producto, id_categoria, id_estilo, nombre, marca, descripcion, precio, existencias, ruta_imagen,status) 
VALUES  
(1, 1, 1, 'Nike Dunk Low Retro', 'Nike','Calzado para Hombre', 105.00, 100,  'https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.5/h_316,c_limit/eba7a509-73b5-473f-a964-6bea77d8ebf1/calzado-dunk-low-retro-76KnBL.png', 'Activo'),
(2, 2, 4,'Sabrina 1 "BKLYN" Finest',  'Adidas','Calzado de Basquetbol', 130.00, 50, 'https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.5/h_316,c_limit/ec6ac4fe-7638-4a64-a7a0-6b9d045af711/calzad-de-b%C3%A1squetbol-sabrina-1-bklyns-finest-f8jr2H.png', 'Activo'),
(3, 2, 1, 'Nike Dunk Low', 'Nike', 'Calzado para Mujer', 130.00, 100,'https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.5/h_316,c_limit/ac89cfe2-a425-41b4-b51a-facde9df1215/calzado-dunk-low-KxSmbV.png', 'Activo'),
(4, 1, 1, 'Cuck Taylor All Star EVA Lift Platform Y2K Heart', 'Converse',  'Niño Zapatillas High Top', 96.00, 50, 'https://www.converse.com/dw/image/v2/BJJF_PRD/on/demandware.static/-/Sites-cnv-master-catalog-we/default/dw70326ae6/images/a_08/A09121C_A_08X1.jpg', 'Activo'),
(5, 2, 1, 'Chuck Taylor All Star Ultra Retro','Converse',  'Pequeño Zapatillas Mid Top',  89.00, 50, 'https://www.converse.com/dw/image/v2/BJJF_PRD/on/demandware.static/-/Sites-cnv-master-catalog-we/default/dwe3c4cdcf/images/a_08/A06376C_A_08X1.jpg', 'Activo'),
(6, 1, 1,'Nike Air Force 1', 'Nike ', 'Calzado para Hombre', 120.00, 80, 'https://th.bing.com/th/id/R.01de72cd25bb3d2591933b953212d1cf?rik=%2ffZd9lQcC5HZiA&pid=ImgRaw&r=0', 'Activo'),
(7, 1, 2, 'Adidas Ultraboost', 'Adidas', 'Calzado para Hombre', 180.00, 60, 'https://www.sneakerfiles.com/wp-content/uploads/2020/12/adidas-ultra-boost-1-0-dna-cookies-and-cream-h68156-release-date-info-3.jpg', 'Activo'),
(8, 2, 1,'Reebok Classic Leather','Reebok ', 'Calzado para Mujer', 90.00, 70, 'https://th.bing.com/th/id/R.28a894944e9d8300a632ca1bbf768896?rik=ynn3uZTieeIwOg&pid=ImgRaw&r=0', 'Activo'),
(9, 2, 1, 'Puma Suede Classic', 'Puma ', 'Calzado para Mujer', 75.00, 90, 'https://th.bing.com/th/id/OIP.XD9mKGrLiktO2d1ww6H6NwHaHa?rs=1&pid=ImgDetMain', 'Activo'),
(10, 1, 6,'Vans Old Skool', 'Vans ',  'Zapatillas Unisex', 65.00, 120, 'https://th.bing.com/th/id/OIP.-nrrz_DO3pzqXoqyKCYcIQHaFj?rs=1&pid=ImgDetMain', 'Activo'),
(11, 1, 1, 'Converse Chuck 70', 'Converse ', 'Zapatillas Unisex', 80.00, 100, 'https://th.bing.com/th/id/OIP.lu8wVXM76sgBqY8b5xIztgHaHa?rs=1&pid=ImgDetMain', 'Activo'),
(12, 1, 5, 'Nike Air Jordan 1','Nike ',  'Calzado de Baloncesto', 160.00, 40, 'https://th.bing.com/th/id/R.6f8418b5e3a69e289a02796a4254523a?rik=jiecpz2uxL9OQA&pid=ImgRaw&r=0g', 'Activo'),
(13, 2, 1,'Adidas Stan Smith','Adidas ',  'Calzado para Mujer', 100.00, 60, 'https://th.bing.com/th/id/R.94bc9a7671751b5cd7f31a6ea7289da1?rik=SRqhe7mtItrcyQ&pid=ImgRaw&r=0', 'Activo'),
(14, 1, 1,'New Balance 574','New Balance ',  'Zapatillas Unisex', 85.00, 80, 'https://th.bing.com/th/id/R.5b00cd0f5954bf686c8623f6a1de4166?rik=rgUD3FSWm0ZQIg&pid=ImgRaw&r=0', 'Activo'),
(15, 1, 5,'Lebron Witness 5', 'Nike' ,'Calzado de Baloncesto', 140.00, 30, 'https://s7d2.scene7.com/is/image/academy/20619160?$pdp-gallery-ng$', 'Activo'),
(16, 1, 1,'Converse Plattform','Converse' , 'Calzado de Trail Running', 150.00, 50, 'https://th.bing.com/th/id/R.7461dd5b08e428b86ea45658812e91f9?rik=D%2fQzp6D5UawcTQ&pid=ImgRaw&r=0', 'Activo'),
(17, 2, 1,'Nike Dunk Low Pink', 'Nike' ,'Calzado para Mujer', 80.00, 70, 'https://th.bing.com/th/id/OIP.SX016tTS9SwTCqSnoXx9JAHaFM?rs=1&pid=ImgDetMain', 'Activo'),
(18, 1, 1,'Puma Clyde','Puma' , 'Calzado para Hombre', 110.00, 60, 'https://th.bing.com/th/id/OIP.mbNJrXfCsYSYQ3dnuOp6JQHaFq?rs=1&pid=ImgDetMain', 'Activo'),
(19, 2, 1,'Nike Dunk Low Blue','Nike' , 'Calzado para Mujer', 120.00, 50, 'https://th.bing.com/th/id/OIP.Wlqh-P05i7Af9GT3LjOgtAHaE-?rs=1&pid=ImgDetMain', 'Activo'),
(20, 1, 5,'Lebron 18', 'Nike' ,'Calzado de Baloncesto', 130.00, 40, 'https://th.bing.com/th/id/OIP.1nXajKQJnNd3Byu6bUJHuwHaHa?rs=1&pid=ImgDetMain', 'Activo'),
(21, 1, 1,'Nike Dunk Low Mocha Brown','Nike ',' Calzado para Hombre', 140.00, 70, 'https://solesavy.com/wp-content/uploads/2021/08/air-jordan-1-low-mocha-brown-dc6991-200-release-information-3.jpg', 'Activo'),
(22, 2, 4,'Adidas NMD R1','Adidas ', 'Calzado para Mujer', 150.00, 80, 'https://th.bing.com/th/id/OIP.FIHNDjRjbMA_ax1G17A7BQHaE8?rs=1&pid=ImgDetMain', 'Activo'),
(23, 2, 2, 'Nike Air Max 270', 'Nike ','Calzado para Mujer', 160.00, 60, 'https://cms-cdn.thesolesupplier.co.uk/2021/08/nike-air-max-2090-gs-black-white-dd3236-001-tsw_w672_h672_pad_.jpg.webp', 'Activo'),
(24, 1, 1, 'Puma Smash', 'Puma ','Calzado de Hombre', 140.00, 50, 'https://www.tennis-point.es/dw/image/v2/BBDP_PRD/on/demandware.static/-/Sites-master-catalog/default/dwc18a59dc/images/079/150/00344000_0_7.jpg?q=80&sw=2000', 'Activo'),
(25, 1, 5, 'Jordan Delta', 'Nike ','Calzado para Hombre', 140.00, 70, 'https://th.bing.com/th/id/R.2105206309a169849d0c75430dbce9d2?rik=VFLxAvg9kM9ZgQ&pid=ImgRaw&r=0', 'Activo');

INSERT INTO FAEJ.factura (id_usuario, fecha, total, estado, direccion, metodo_pago, ruta_imagen) VALUES
(3, '2024-04-01', 150.00, 'Entregada', '123 Calle Principal, San José, Costa Rica', 'Tarjeta de crédito', '/images/receipts/0001.jpg'),
(3, '2024-04-02', 200.50, 'Entregada', '456 Avenida Secundaria, Cartago, Costa Rica', 'PayPal', '/images/receipts/0002.jpg'),
(3, '2024-04-03', 99.99, 'Pendiente', '789 Calle Terciaria, Heredia, Costa Rica', 'Transferencia bancaria', '/images/receipts/0003.jpg'),
(4, '2024-04-04', 300.75, 'Camino', '321 Calle Cuarta, Alajuela, Costa Rica', 'Tarjeta de crédito', '/images/receipts/0004.jpg'),
(4, '2024-04-05', 450.00, 'Nueva', '654 Avenida Quinta, Limón, Costa Rica', 'Efectivo', '/images/receipts/0005.jpg');

INSERT INTO FAEJ.venta (id_factura, id_producto, precio, cantidad, nombre_producto, nombre_usuario, ruta_imagen) VALUES
(1, 1, 105.00, 2, 'Nike Dunk Low Retro', 'Ariana', 'https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.5/h_316,c_limit/eba7a509-73b5-473f-a964-6bea77d8ebf1/calzado-dunk-low-retro-76KnBL.png'),
(2, 2, 130.00, 1, 'Sabrina 1 "BKLYN" Finest', 'Ariana', 'https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.5/h_316,c_limit/ec6ac4fe-7638-4a64-a7a0-6b9d045af711/calzad-de-b%C3%A1squetbol-sabrina-1-bklyns-finest-f8jr2H.png'),
(3, 3, 130.00, 3, 'Nike Dunk Low', 'Fernanda', 'https://static.nike.com/a/images/q_auto:eco/t_product_v1/f_auto/dpr_1.5/h_316,c_limit/ac89cfe2-a425-41b4-b51a-facde9df1215/calzado-dunk-low-KxSmbV.png'),
(4, 4, 96.00, 2, 'Cuck Taylor All Star EVA Lift Platform Y2K Heart', 'Fernanda', 'https://www.converse.com/dw/image/v2/BJJF_PRD/on/demandware.static/-/Sites-cnv-master-catalog-we/default/dw70326ae6/images/a_08/A09121C_A_08X1.jpg'),
(5, 5, 89.00, 1, 'Chuck Taylor All Star Ultra Retro', 'Jorge', 'https://www.converse.com/dw/image/v2/BJJF_PRD/on/demandware.static/-/Sites-cnv-master-catalog-we/default/dwe3c4cdcf/images/a_08/A06376C_A_08X1.jpg'); 

insert into faej.rol (id_rol, nombre, id_usuario) values
 (1,'ROLE_ADMIN',1), (2,'ROLE_VENDEDOR',2), (3,'ROLE_USER',3);
 
INSERT INTO faej.tallas(id_talla, talla, id_producto) VALUES
(1, 7, 1),(2, 7.5, 1),(3, 7, 5);
INSERT INTO faej.color(id_color, color, id_producto) VALUES
(1, 'Blanco', 1),(2, 'Beige', 1),(3, 'Blanco', 5);