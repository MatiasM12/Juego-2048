package Interfaz;

import java.awt.EventQueue;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Logica.Tablero;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.border.LineBorder;
import java.awt.Dimension;

public class Interfaz2048 implements KeyListener{

	private JFrame frmJuego;
	private JTable table;
	private Tablero tablero;
	private String[] columnas;
	private String puntos;
	private Clip musica;
	private JLabel puntaje;
	private JTextPane txtpnGanaste;
	private JTextPane txtpnPerdiste_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz2048 window = new Interfaz2048();
					window.frmJuego.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz2048() {
		initialize();		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		tablero= new Tablero(4);
		
		
		//Ventana
		frmJuego = new JFrame();
		frmJuego.getContentPane().setSize(new Dimension(500000, 2147483647));
		frmJuego.setTitle("2048 - Testigos de Java");
		frmJuego.getContentPane().setBackground(new Color(102, 204, 255));
		frmJuego.setBounds(100, 100, 875, 650);
		frmJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJuego.getContentPane().setLayout(null);
		
		
		
		
		 
		
		//Tabla
		table = new JTable();
		table.setBackground(new Color(153, 204, 255));
		table.setSelectionForeground(Color.WHITE);
		table.setFont(new Font("Tahoma", Font.PLAIN, 62));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setRowHeight(110);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setBounds(98, 69, 360, 333);
		table.setAutoCreateRowSorter(true);
		table.setForeground(Color.BLACK);
		table.setSelectionBackground(Color.LIGHT_GRAY);
		table.setEnabled(false);
		table.setBounds(98, 100, 660, 440);
		frmJuego.getContentPane().add(table);
	
		columnas =new String[] {"0", "1", "2", "3"};
		
		table.setModel(new DefaultTableModel(tablero.getGrilla(),columnas));

		//Musica
		try {
			musica = AudioSystem.getClip();
			musica.open(AudioSystem.getAudioInputStream(new File("musicaJuego.wav")));
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		musica.start();
		
		
		//Titulo
		JLabel lblNewLabel = new JLabel("Juego 2048");
		lblNewLabel.setBounds(26, 11, 185, 47);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		frmJuego.getContentPane().add(lblNewLabel);
		
		//Puntaje
		puntos="Puntos: "+tablero.getPuntos();
		puntaje = new JLabel(puntos);
		puntaje.setText(puntos);
		puntaje.setFont(new Font("Arial", Font.PLAIN, 25));
		puntaje.setBounds(617, 14, 206, 45);
		frmJuego.getContentPane().add(puntaje);
		

		frmJuego.addKeyListener(this);


		
		}


	

	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(key == KeyEvent.VK_UP) {
			tablero.moverArriba();
			table.setModel(new DefaultTableModel(tablero.getGrilla(),columnas));
		}
		if(key == KeyEvent.VK_DOWN) {
			tablero.moverAbajo();
			table.setModel(new DefaultTableModel(tablero.getGrilla(),columnas));

		}
		if(key == KeyEvent.VK_RIGHT) {
			tablero.moverDerecha();
			table.setModel(new DefaultTableModel(tablero.getGrilla(),columnas));

		}
		if(key == KeyEvent.VK_LEFT) {
			tablero.moverIzquierda();
			table.setModel(new DefaultTableModel(tablero.getGrilla(),columnas));

		}
		
//---------- para actualizar la puntuación-----------------
		
		puntos="Puntos: "+tablero.getPuntos();
		puntaje.setText(puntos);
		
//--------- para cartel de perdio-------------------------------- 
		
		if(tablero.perdio()) {			
			txtpnPerdiste_1 = new JTextPane();
			txtpnPerdiste_1.setBackground(new Color(255, 0, 0));
			txtpnPerdiste_1.setFont(new Font("Gill Sans MT Ext Condensed Bold", Font.PLAIN, 70));
			txtpnPerdiste_1.setText("   PERDISTE!!");
			txtpnPerdiste_1.setBounds(236, 121, 432, 360);
			txtpnPerdiste_1.setVisible(true);
			table.setVisible(false);
			frmJuego.getContentPane().add(txtpnPerdiste_1);
		}
				
//----------- para cartel de ganó ------------------------------------
		
		
		if(tablero.gano()) {
			txtpnGanaste = new JTextPane();
			txtpnGanaste.setBackground(new Color(0, 255, 127));
			txtpnGanaste.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.PLAIN, 99));
			txtpnGanaste.setText("   GANASTE!");
			txtpnGanaste.setBounds(236, 121, 432, 360);
			txtpnGanaste.setVisible(true);
			table.setVisible(false);
			frmJuego.getContentPane().add(txtpnGanaste);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}





