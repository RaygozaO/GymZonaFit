package com.ors.zona_fit.gui;

import com.ors.zona_fit.modelo.Cliente;
import com.ors.zona_fit.servicio.ClienteServicio;
import com.ors.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class ZonaFitForma extends JFrame{
    private JPanel panelPrincipal;
    private JPanel panelTable;
    private JPanel inputTable;
    private JTable clientesTable;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField emailTexto;
    private JTextField memTexto;
    private JButton Guardar;
    private JButton Eliminar;
    private JButton limpiarButton;
    IClienteServicio clienteServicio;
    private DefaultTableModel tableModel;
    private Integer idCliente;

    @Autowired
    public ZonaFitForma(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
        inciarForma();
        Guardar.addActionListener(e -> guardarCliente());
        clientesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarClienteSel();
            }
        });
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarClienteSel();
                eliminarCliente();
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormualario();
            }
        });
    }
    private void inciarForma() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
         this.tableModel = new DefaultTableModel(0,5);
         String[] cabeceros = {"ID", "Nombre", "Apellido", "E-Mail","Membresia"};
         this.tableModel.setColumnIdentifiers(cabeceros);
         this.clientesTable = new JTable(this.tableModel);
         //cargar el listado de clientes
        listarClientes();
    }
    private void listarClientes() {
        this.tableModel.setRowCount(0);
        var clientes = this.clienteServicio.listar();
        clientes.forEach(cliente -> {
           Object[] renglonCliente ={
                   cliente.getId_cliente(),
                   cliente.getNombre(),
                   cliente.getApellido(),
                   cliente.getEmail(),
                   cliente.getMembresia()
           };
           this.tableModel.addRow(renglonCliente);
        });
    }
    private void cargarClienteSel() {
        var renglon = this.clientesTable.getSelectedRow();
        if(renglon != -1){
            var id = clientesTable.getModel().getValueAt(renglon, 0).toString();
            this.idCliente = Integer.parseInt(id);
            var nombre = this.clientesTable.getModel().getValueAt(renglon, 1).toString();
            this.nombreTexto.setText(nombre);
            var apellido = this.clientesTable.getModel().getValueAt(renglon, 2).toString();
            this.apellidoTexto.setText(apellido);
            var email = this.clientesTable.getModel().getValueAt(renglon, 3).toString();
            this.emailTexto.setText(email);
            var mem = this.clientesTable.getModel().getValueAt(renglon, 4).toString();
            this.memTexto.setText(mem);
        }
    }
    private void guardarCliente() {
        if(nombreTexto.getText().equals("")){
            mostrarMensaje("Debe ingresar el nombre");
            nombreTexto.requestFocusInWindow();
            return;
        }
        if(apellidoTexto.getText().equals("")){
            mostrarMensaje("Debe ingresar el apellido");
            apellidoTexto.requestFocusInWindow();
            return;
        }
        if(emailTexto.getText().equals("")){
            mostrarMensaje("Debe ingresar el email");
            emailTexto.requestFocusInWindow();
            return;
        }
        if(memTexto.getText().equals("")){
            mostrarMensaje("Debe ingresar la membresia");
            memTexto.requestFocusInWindow();
            return;
        }
        var nombre = nombreTexto.getText();
        var apellido = apellidoTexto.getText();
        var email = emailTexto.getText();
        var mem = memTexto.getText();
        var cliente = new Cliente(this.idCliente, nombre, apellido, email, mem);
        cliente.setId_cliente(this.idCliente);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEmail(email);
        cliente.setMembresia(mem);
        this.clienteServicio.guardar(cliente);
        if(this.idCliente == null){
            mostrarMensaje("Cliente guardado");
        }else{
            mostrarMensaje("Cliente actualizado");
        }
        limpiarFormualario();
        listarClientes();
    }
    private void eliminarCliente() {
        var renglon = this.clientesTable.getSelectedRow();
        if(renglon != -1){
            var id = clientesTable.getModel().getValueAt(renglon, 0).toString();
            this.idCliente = Integer.parseInt(id);
            var nombre = this.clientesTable.getModel().getValueAt(renglon, 1).toString();
            this.nombreTexto.setText(nombre);
            var apellido = this.clientesTable.getModel().getValueAt(renglon, 2).toString();
            this.apellidoTexto.setText(apellido);
            var email = this.clientesTable.getModel().getValueAt(renglon, 3).toString();
            this.emailTexto.setText(email);
            var mem = this.clientesTable.getModel().getValueAt(renglon, 4).toString();
            this.memTexto.setText(mem);
            var cliente = new Cliente(this.idCliente,nombre,apellido,email,mem);
            this.clienteServicio.eliminar(cliente);
        }else{
            mostrarMensaje("Debe seleccionar un Cliente");
        }
        limpiarFormualario();
        listarClientes();
    }
    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    private void limpiarFormualario() {
        idCliente = null;
        nombreTexto.setText("");
        apellidoTexto.setText("");
        emailTexto.setText("");
        memTexto.setText("");
        // Deseleccionamos el registro de la tabla
        this.clientesTable.getSelectionModel().clearSelection();
    }
}
