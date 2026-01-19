-- Tabla: Categorias
drop table if exists categorias;
create table categorias (
    codigo_cat serial not null,
    nombre varchar(100) not null,
    categoria_padre int,
    constraint categorias_pk primary key (codigo_cat),
    constraint categorias_fk foreign key (categoria_padre) references categorias(codigo_cat)
);

-- tabla: categorias_unidad_medida
drop table if exists categorias_unidad_medida;
create table categorias_unidad_medida (
    codigo_cat_uni char(1) not null,
    nombre varchar(100) not null,
    constraint categorias_unidad_medida_pk primary key (codigo_cat_uni)
);

-- tabla: categorias_unidad_medida
drop table if exists unidad_medida;
create table unidad_medida (
    codigo_udm char(2) not null,
    descripcion varchar(100) not null,
    categoria_udm char(1) not null,
    constraint codigo_udm_pk primary key (codigo_udm),
    constraint categorias_unidad_medida_fk foreign key (categoria_udm) references categorias_unidad_medida(codigo_cat_uni)
);


-- tabla: producto
drop table if exists producto;
create table producto (
    codigo serial not null ,
    nombre varchar(100) not null,
    udm char(2) not null,
    precio_venta money not null,
    tiene_iva boolean not null,
    coste money not null,
    categoria_serial int not null,
    stock int not null,
    constraint producto_pk primary key (codigo_pro),
    constraint  umd_fk foreign key (udm) references unidad_medida(codigo_udm),
    constraint categoria_fk foreign key (categoria_serial) references categorias(codigo_cat)
);

alter table producto
drop COLUMN codigo_pro;
alter table producto ADD COLUMN codigo serial;
alter table producto add constraint producto_pk primary key (codigo);



-- tabla: tipo_documento
drop table if exists tipo_documento;
create table tipo_documento (
    codigo_td char(1)not null,
    descripcion varchar(100) not null,
    constraint tipo_documento_pk primary key (codigo_td)
);

-- tabla: proveedor
drop table if exists proveedor;
create table proveedor (
    identificador varchar(100) not null,
    tipo_documento char(1) not null,
    nombre varchar(100) not null,
    telefono varchar(100) not null,
    correo varchar(100) not null,
    direccion varchar(100) not null,
    constraint proveedor_pk primary key (identificador),
    constraint tipo_documento_fk foreign key (tipo_documento) references tipo_documento(codigo_td)
);

-- tabla: cabecera_pedido
drop table if exists cabecera_pedido;
create table cabecera_pedido (
    numero_serial serial not null,
    proveedor varchar(100) not null,
    fecha date not null,
    estado char(1) not null,
    constraint cabecera_pedido_pk primary key (numero_serial)
);

-- tabla: detalle_pedido
drop table if exists detalle_pedido;
create table detalle_pedido (
    codigo_serial serial not null,
    cabecera_pedido int not null,
    producto int,
    cantidad money not null,
    subtotal money not null,
    constraint detalle_pedido_pk primary key (codigo_serial),
    constraint cabecera_pedido_fk foreign key (cabecera_pedido) references cabecera_pedido(numero_serial),
    constraint producto_fk foreign key (producto) references producto(codigo_pro)
);

alter table detalle_pedido drop COLUMN
 producto;
 alter table detalle_pedido drop constraint producto_fk; 

 alter table detalle_pedido add COLUMN producto int;
 alter table detalle_pedido  add constraint   producto_fk foreign key (producto) references producto(codigo);

-- tabla: estado_pedidos
drop table if exists estado_pedidos;
create table estado_pedidos (
    codigo char(1) not null,
    nombre varchar(100) not null,
    constraint estado_pedidos_pk primary key (codigo)
);

-- tabla: historial_stock
drop table if exists historial_stock;
create table historial_stock (
    codigo char(1) not null ,
    fecha timestamp without time zone not null,
    referencia varchar(100) not null,
    cantidad int not null,
    subtotal money not null,
    producto char(1) not null,
    cantidad_recibida int not null,
    constraint codigo_pk primary key (codigo),
    constraint producto_fk foreign key (producto) references producto(codigo_pro)
);

alter table historial_stock drop COLUMN
 producto;
 alter table historial_stock drop constraint producto_fk;


  alter table historial_stock add COLUMN producto int;
 alter table historial_stock add constraint   producto_fk foreign key (producto) references producto(codigo);

-- tabla: cabecera_venta
drop table if exists cabecera_venta;
create table cabecera_venta (
    codigo serial not null,
    fecha timestamp without time zone not null,
    total_sin_iva money not null,
    total money not null,
    iva money not null,
    constraint cabecera_venta_pk primary key (codigo)
);

-- tabla: detalle_ventas
drop table if exists detalle_ventas;
create table detalle_ventas (
    codigo char(1) not null,
    producto int not null,
    cantidad int not null,
    precio_venta money not null,
    subtotal money not null,
    subtotal_iva money not null,
    cabecera_venta int not null,
    constraint detalle_ventas_pk primary key (codigo),
    constraint producto_fk foreign key (producto) references producto(codigo_pro),
    constraint cabecera_venta_fk foreign key (cabecera_venta) references cabecera_venta(codigo)
);
alter table detalle_ventas drop COLUMN
 producto;
  alter table detalle_ventas drop constraint producto_fk;

   alter table detalle_ventas add COLUMN producto int;
 alter table detalle_ventas  add constraint   producto_fk foreign key (producto) references producto(codigo);

--LOS INSERTS

-- Insertar en categorias
INSERT INTO categorias (nombre, categoria_padre) VALUES
('proteína', 1),
('salsas', 1),
('punto de venta', NULL),
('bebidas', 4),
('con alcohol', 5),
('sin alcohol', 5);
select * from categorias;
-- Insertar en categorias_unidad_medida
INSERT INTO categorias_unidad_medida (codigo_cat_uni, nombre) VALUES
('u', 'unidades'),
('v', 'volumen'),
('p', 'peso');
select * from categorias_unidad_medida;
-- Insertar en unidades_medida
INSERT INTO unidad_medida (codigo_udm, descripcion, categoria_udm) VALUES
('ml', 'mililitros', 'v'),
('l', 'litros', 'v'),
('u', 'unidad', 'u'),
('d', 'docena', 'u'),
('kg', 'kilogramos', 'p');

select * from unidad_medida;

INSERT INTO producto (nombre, udm, precio_venta, tiene_iva, coste, categoria_serial, stock) VALUES
( 'coca cola pequeña', 'ml', 0.5804, true, 0.3729, 7, 105),
( 'salsa de tomate', 'kg', 0.95, true, 0.8736, 3, 0),
( 'mostaza', 'kg', 0.95, true, 0.89, 3, 0),
( 'fuze tea', 'u', 0.8, true, 0.7, 7, 49);

select * from producto;
INSERT INTO tipo_documento (codigo_td, descripcion) VALUES
('c', 'cedula'),
('r', 'ruc');

select * from proveedor;

INSERT INTO proveedor (identificador, tipo_documento, nombre, telefono, correo, direccion) VALUES
('1792285747', 'c', 'santiago mos', '0992920306', 'zantycb89@', 'cumbayork'),
('1792285747001', 'r', 'snacks sa', '0992920398', 'snacks@gmai', 'la tola');

select * from estado_pedidos;

INSERT INTO estado_pedidos (codigo, nombre) VALUES
('s', 'solicitado'),
('r', 'recibido');
select * from estado_pedidos;
INSERT INTO cabecera_pedido (numero_serial, proveedor, fecha, estado) VALUES
(1, '1792285747001', '2023-11-20', 's'),
(2, '1792285747001', '2023-11-20', 's');

select * from cabecera_pedido;
INSERT INTO detalle_pedido (codigo_serial, cabecera_pedido, producto, cantidad, subtotal) VALUES
(1, 1, '1', 100, 37.29),
(2, 1, '4', 50, 11.8),
(3, 2, '1', 10, 3.73);
select * from detalle_pedido;

INSERT INTO historial_stock (codigo, fecha, referencia, producto, cantidad, subtotal, cantidad_recibida) VALUES
('1', '2023-11-20 19:59:00', 'pedido 1', '1', 100, 37.29, 100),
('2', '2023-11-20 19:59:00', 'pedido 1', '4', 50, 11.8, 50),
('3', '2023-11-20 20:00:00', 'pedido 2', '1', 10, 3.73, 10),
('4', '2023-11-20 20:00:00', 'venta 1', '1', 5, 2.91, 0),
('5', '2023-11-20 20:00:00', 'venta 1', '4', 1, 0.8, 0);
select * from historial_stock;

INSERT INTO cabecera_venta (codigo, fecha, total_sin_iva, iva, total) VALUES
(1, '2023-11-20 20:00:00', 3.26, 0.39, 3.65);

select * from cabecera_venta;
INSERT INTO detalle_ventas (codigo, producto, cantidad, precio_venta, subtotal, subtotal_iva, cabecera_venta) VALUES
('1', '1', 5, 0.58, 2.9, 3.25, 1),
('2', '4', 1, 0.36, 0.36, 0.4, 1);
select * from detalle_ventas;