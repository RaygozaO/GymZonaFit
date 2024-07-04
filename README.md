Repositorio de ejemplo de un CRUD realizado en Spring de back y Swing de FrontEnd

para Levanatar Spring se necesita hacer la Base de Datos las instrucciones son:
las credenciales son locales.

CREATE DATABASE zona_fit_db; 
CREATE TABLE cliente (
    idCliente int not null primary key auto_increment,
    nombre varchar(45) not null,
    apellido, varchar(45) not null,
    email varchar(45) not null,
    membresia varchar(45) not null);

INSERT INTO cliente(nombre,apellido,email,mebresia) values
    (Insertar valores de demostracion);

Acomodar las credenciales de la base de datos en application.properties

