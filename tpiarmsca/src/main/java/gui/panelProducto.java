package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sql.controllers.ProductoController;
import sql.controllers.SucursalController;

public class panelProducto extends JPanel {
	private JTextField Buscador;
	private JTable tablaProductos = new JTable();

	/**
	 * Create the panel.
	 */
	public panelProducto() {
		setBounds(309, 98, 955, 583);
		setLayout(null);
		
		Buscador = new JTextField();
		Buscador.setBounds(151, 77, 627, 27);
		add(Buscador);
		Buscador.setColumns(10);
		
		Buscador.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				filterTable();
			}
			public void removeUpdate(DocumentEvent e) {
				filterTable();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
		});

		// ---------------------------------------------------------------//
		JLabel lblNewLabel = new JLabel("Buscar producto (Nombre producto)");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel.setBounds(151, 52, 257, 14);
		add(lblNewLabel);
		// ---------------------------------------------------------------//
		
		// ---------------------------------------------------------------//
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(151, 125, 624, 299);
		add(scrollPane);
		tablaProductos.setModel(new ProductoController().generadorDeTabla());
		tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(115);
		tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(99);
		tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tablaProductos);
		// ---------------------------------------------------------------//

		// ---------------------------------------------------------------//
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonAgregar.setBackground(new Color(195, 207, 217));
		botonAgregar.setBounds(167, 454, 143, 39);
		botonAgregar.setFocusPainted(false);
		botonAgregar.setBorder(new EmptyBorder(0, 0, 0, 0));
		botonAgregar.addActionListener(e -> {
			agregarProducto.main(null,this);
		});
		add(botonAgregar);
		// ---------------------------------------------------------------//

		// ---------------------------------------------------------------//
		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEliminar.setBackground(new Color(195, 207, 217));
		botonEliminar.setBounds(357, 454, 143, 39);
		botonEliminar.setFocusPainted(false);
		botonEliminar.setBorder(new EmptyBorder(0, 0, 0, 0));
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaProductos.getSelectedRow();
				if (filaSeleccionada != -1) {
					
                    Object idSeleccionado = tablaProductos.getValueAt(filaSeleccionada, 0);
                    int op = JOptionPane.showOptionDialog(null,"Estas seguro que deseas eliminar este producto?","Avertencia",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] {"Si","No"},"Si");
                    switch(op) {
                    	case JOptionPane.YES_OPTION: 
                    		new ProductoController().deleteProducto(Integer.parseInt(idSeleccionado.toString()));
                    		tablaProductos.setModel(new ProductoController().generadorDeTabla());
                    		break;
                    	case JOptionPane.NO_OPTION: 
                    		break;
                    }
                } 
            }
		});
		add(botonEliminar);
		// ---------------------------------------------------------------//

		// ---------------------------------------------------------------//
		JButton botonEditar = new JButton("Editar");
		botonEditar.setFont(new Font("Dialog", Font.BOLD, 15));
		botonEditar.setBackground(new Color(195, 207, 217));
		botonEditar.setBounds(632, 454, 143, 39);
		botonEditar.setFocusPainted(false);
		botonEditar.setBorder(new EmptyBorder(0, 0, 0, 0));
		botonEditar.addActionListener(e->{
			int filaSeleccionada = tablaProductos.getSelectedRow();
			if (filaSeleccionada != -1) {
				Object idSeleccionado = tablaProductos.getValueAt(filaSeleccionada, 0);
				editarProducto.main(null, this, Integer.parseInt(idSeleccionado.toString()));
			}
		});
		add(botonEditar);
		// ---------------------------------------------------------------//

	}
	
	private void filterTable() {
		tablaProductos.setModel(new ProductoController().filtrarTablaPorNombre(Buscador.getText()));
	}

	public JTable getTablaProductos() {
		return tablaProductos;
	}

	public void setTablaProductos(JTable tablaProductos) {
		this.tablaProductos = tablaProductos;
	}
	
	

}