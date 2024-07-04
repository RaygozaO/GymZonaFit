package com.ors.zona_fit;

import com.ors.zona_fit.modelo.Cliente;
import com.ors.zona_fit.repositorio.ClienteRepositorio;
import com.ors.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	//@Autowired
	private IClienteServicio clienteServicio;
	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);
	String nl = System.lineSeparator();
    //@Autowired
    private ClienteRepositorio clienteRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("ZonaFit Application Inicializada");
	}
	@Override
	public void run(String... args) throws Exception {
		menu();
	}

	public void menu(){
		logger.info(nl +"*** Aplicacion Zona Fit (GYM) ***"+nl);
		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(nl);
		}
	}
	public int mostrarMenu(Scanner consola){
		logger.info("""
				1.- Listar Clientes
				2.- Buscar Cliente
				3.- Agregar Cliente
				4.- Modificar Cliente
				5.- Eliminar Cliente
				6.- Salir
				Elige una Opción: \s
				""");
		return consola.nextInt();
	}
	private boolean ejecutarOpciones(Scanner consola,int opcion){
		var salir = false;
		switch(opcion){
			case 1 ->{
				logger.info(nl+"-- Listado de Clientes ---");
				List<Cliente> clientes = clienteServicio.listar();
				clientes.forEach(cliente -> logger.info(nl+"\t"+cliente.toString()));
			}
			case 2 ->{
				logger.info(nl+"-- Buscar Cliente por ID---"+nl);
				logger.info("Ingrese el ID del Cliente: ");
				var idCliente = consola.nextInt();
				Cliente cliente = clienteServicio.buscarByID(idCliente);
				if(cliente != null){
					logger.info(nl+"\tCliente Encontrado: "+cliente.toString()+nl);
				}else{
					logger.info(nl+"\tCliente no encontrado "+cliente +nl);
				}
			}
			case 3 ->{
				logger.info("-- Agregar Cliente ---"+nl);
				consola.nextLine();
				logger.info("Ingrese el Nombre del Cliente:");
				var nombre = consola.nextLine();
				logger.info("Ingrese el Apellido del Cliente:");
				var apellido = consola.nextLine();
				logger.info("Ingrese el Correo del Cliente:");
				var correo = consola.nextLine();
				logger.info("Ingrese la Membresia del Cliente:");
				var membresia = consola.nextLine();
				Cliente cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setEmail(correo);
				cliente.setMembresia(membresia);
				clienteServicio.guardar(cliente);
				logger.info(nl+"\tCliente Agregado: "+cliente.toString()+nl);
			}
			case 4->{
				logger.info("-- Modificar Cliente ---"+nl);
				logger.info("Ingrese el ID del Cliente:");
				var idCliente = consola.nextInt();
				Cliente cliente = clienteServicio.buscarByID(idCliente);
				if(cliente != null){
					consola.nextLine();
					logger.info("Nombre Cliente: ");
					var nombre = consola.nextLine();
					logger.info("Apellido Cliente: ");
					var apellido = consola.nextLine();
					logger.info("Correo Cliente: ");
					var correo = consola.nextLine();
					logger.info("Membresia Cliente: ");
					var membresia = consola.nextLine();
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setEmail(correo);
					cliente.setMembresia(membresia);
					clienteServicio.guardar(cliente);
					logger.info(nl+"\tCliente Modificado: "+cliente.toString()+nl);
				}
			}
			case 5->{
				logger.info("-- Eliminar Cliente ---"+nl);
				logger.info("Ingrese el ID del Cliente:");
				var idCliente = consola.nextInt();
				Cliente cliente = clienteServicio.buscarByID(idCliente);
				if(cliente != null){
					clienteServicio.eliminar(cliente);
					logger.info(nl+"\tCliente Eliminado: "+cliente.toString()+nl);
				}else{
					logger.info(nl+"\tCliente no encontrado "+cliente +nl);
				}
			}
			case 6 ->{
				logger.info("-- Saliendo de la aplicacion ---"+nl);
				salir = true;
			}
			default -> logger.info("Opcion no reconocida "+nl);
		}
		return salir;
	}

}
