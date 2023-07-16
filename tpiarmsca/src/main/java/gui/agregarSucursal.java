package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import sql.controllers.SucursalController;
import sql.models.SucursalModel;

public class agregarSucursal extends JFrame {

	private JPanel contentPane;
	private JTextField campoNombre;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args,panelSucursal panel) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					agregarSucursal frame = new agregarSucursal(panel);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public agregarSucursal(panelSucursal panel) {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(214, 214, 214));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setSize(350,500);
		setResizable(false);
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoNombre = new JTextField();
		campoNombre.setColumns(10);
		campoNombre.setBounds(70, 63, 171, 20);
		contentPane.add(campoNombre);
		//--------------------------------------------//
		//array con las opciones de horas (00 a 23)
        String[] horas = new String[24];
        for (int i = 0; i < 24; i++) {
            horas[i] = String.format("%02d", i);
        }

        //array con las opciones de minutos (00 a 59)
        String[] minutos = new String[60];
        for (int i = 0; i < 60; i++) {
            minutos[i] = String.format("%02d", i);
        }
		
		//--------------------------------------------//
		JComboBox<String> comboHoras = new JComboBox<>(horas);
		comboHoras.setBounds(70, 138, 42, 22);
		contentPane.add(comboHoras);
		
		JComboBox<String> comboMinutos = new JComboBox<>(minutos);
		comboMinutos.setBounds(122, 138, 47, 22);
		contentPane.add(comboMinutos);
		
		final String[] horaApertura = { "00:00" };
		
		comboHoras.addActionListener(e -> actualizarHora(horaApertura, comboHoras, comboMinutos));
		comboMinutos.addActionListener(e -> actualizarHora(horaApertura, comboHoras, comboMinutos));
		
		JComboBox<String> comboHoras2 = new JComboBox<>(horas);
		comboHoras2.setBounds(70, 225, 42, 22);
		contentPane.add(comboHoras2);
		
		JComboBox<String> comboMinutos2 = new JComboBox<>(minutos);
		comboMinutos2.setBounds(122, 225, 47, 22);
		contentPane.add(comboMinutos2);
		
		final String[] horaCierre = { "00:00" };
		comboHoras2.addActionListener(e -> actualizarHora(horaCierre, comboHoras2, comboMinutos2));
		comboMinutos2.addActionListener(e -> actualizarHora(horaCierre, comboHoras2, comboMinutos2));
		
		JLabel nombreSucursal = new JLabel("Nombre");
		nombreSucursal.setFont(new Font("Dialog", Font.BOLD, 13));
		nombreSucursal.setBounds(70, 38, 58, 14);
		contentPane.add(nombreSucursal);
		
		JLabel horarioApertura = new JLabel("Horario apertura");
		horarioApertura.setFont(new Font("Dialog", Font.BOLD, 13));
		horarioApertura.setBounds(70, 113, 114, 14);
		contentPane.add(horarioApertura);
		
		JLabel horarioCierre = new JLabel("Horario cierre");
		horarioCierre.setFont(new Font("Dialog", Font.BOLD, 13));
		horarioCierre.setBounds(70, 190, 90, 14);
		contentPane.add(horarioCierre);
		
		JLabel estado = new JLabel("Estado");
		estado.setFont(new Font("Dialog", Font.BOLD, 13));
		estado.setBounds(70, 270, 46, 14);
		contentPane.add(estado);
		
		JComboBox<String> estadoTipo = new JComboBox<String>();
		estadoTipo.setBounds(70, 295, 95, 22);
		estadoTipo.addItem("Operativo");
		estadoTipo.addItem("No operativo");
		contentPane.add(estadoTipo);
		
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Dialog", Font.BOLD, 13));
		botonAgregar.setBounds(142, 355, 99, 35);
		botonAgregar.addActionListener(e->{
					SucursalController sucursal = new SucursalController();
					sucursal.createSucursal(campoNombre.getText(), horaApertura[0], horaCierre[0],estadoTipo.getSelectedItem()=="Operativo");
					panel.getTablaSucursales().setModel(new SucursalController().generadorDeTabla());
					dispose();
		});
		contentPane.add(botonAgregar);
		
		
	}
	
	private void actualizarHora(String[] hora, JComboBox<String> comboHoras, JComboBox<String> comboMinutos) {
	    String horaSeleccionada = comboHoras.getSelectedItem().toString();
	    String minutosSeleccionados = comboMinutos.getSelectedItem().toString();
	    hora[0] = String.format("%02d:%02d", Integer.parseInt(horaSeleccionada), Integer.parseInt(minutosSeleccionados));
	}


	public JTextField getCampoNombre() {
		return campoNombre;
	}

	public void setCampoNombre(JTextField campoNombre) {
		this.campoNombre = campoNombre;
	}

	

	
}
