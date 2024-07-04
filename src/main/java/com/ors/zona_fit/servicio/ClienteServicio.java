package com.ors.zona_fit.servicio;

import com.ors.zona_fit.modelo.Cliente;
import com.ors.zona_fit.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServicio implements IClienteServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Override
    public List<Cliente> listar() {
        List<Cliente> clientes = clienteRepositorio.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarByID(Integer idCliente) {
        Cliente cliente = clienteRepositorio.findById(idCliente).orElse(null);
        return cliente;
    }

    @Override
    public void guardar(Cliente cliente) {
        clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminar(Cliente cliente) {
        clienteRepositorio.delete(cliente);
    }

    @Autowired
    public void setClienteRepositorio(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }
}
