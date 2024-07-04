package com.ors.zona_fit.servicio;

import com.ors.zona_fit.modelo.Cliente;

import java.util.List;


public interface IClienteServicio {
    public List<Cliente> listar();
    public Cliente buscarByID(Integer idCliente);
    public void guardar(Cliente cliente);
    public void eliminar(Cliente cliente);
}
