package Logica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TableroTest {

	private Tablero tablero;
	
	@Before
	public void inicializacion() {
		tablero = new Tablero();
		tablero.agregar(0, 3, 4);
		tablero.agregar(1, 0, 2);
		tablero.agregar(2, 0, 8);
		tablero.agregar(1, 2, 2);
		tablero.agregar(2, 3, 2);
		tablero.agregar(3, 1, 4);
		tablero.agregar(2, 1, 4);
		tablero.agregar(3, 3, 8);
	}
	
	@Test
	public void moverArribaTest() {
		tablero.moverArriba();	
		
		assertEquals(2,tablero.getGrilla()[0][0].getValor());
		assertEquals(8,tablero.getGrilla()[1][0].getValor());
		assertEquals(8,tablero.getGrilla()[0][1].getValor());
		assertEquals(2,tablero.getGrilla()[0][2].getValor());
		assertEquals(4,tablero.getGrilla()[0][3].getValor());
		assertEquals(2,tablero.getGrilla()[1][3].getValor());
		}
	
	@Test
	public void moverAbajoTest() {
		tablero.moverAbajo(); 		
//		null|null|null|null|
//		null|null|null|4   |
//		2   |null|null|2   |
//		8   |8   |2   |8   |
		
		assertEquals(8,tablero.getGrilla()[3][3].getValor());
		assertEquals(8,tablero.getGrilla()[3][0].getValor());
		assertEquals(2,tablero.getGrilla()[2][3].getValor());
	}
	 
	@Test
	public void moverDerechaTest() {
		tablero.moverDerecha();		
//		null|null|null|4   |
//		null|null|null|4   |
//		null|8   |4   |2   |
//		null|null|4   |8   |
		
		assertEquals(4,tablero.getGrilla()[0][3].getValor());
		assertEquals(4,tablero.getGrilla()[1][3].getValor());
		assertEquals(4,tablero.getGrilla()[2][2].getValor());
	}
	
	@Test
	public void moverIzquierdaTest() {
		tablero.moverIzquierda();		
//		4   |null|null|null|
//		4   |null|null|null|
//		8   |4   |2   |null|
//		4   |8   |null|null|
		
		assertEquals(4,tablero.getGrilla()[0][0].getValor());
		assertEquals(4,tablero.getGrilla()[3][0].getValor());
		assertEquals(8,tablero.getGrilla()[3][1].getValor());
	}	
	
	@Test
	public void combinarAbajoTest() {
		tablero.moverAbajo(); 		
//		null|null|null|null|
//		null|null|null|4   |
//		2   |null|null|2   |
//		8   |8   |2   |8   |
		
		assertEquals(8,tablero.getGrilla()[3][1].getValor());
	}
	
	@Test
	public void combinarDerechaTest() {
		tablero.moverDerecha();		
//		null|null|null|4   |
//		null|null|null|4   |
//		null|8   |4   |2   |
//		null|null|4   |8   |
		
		assertEquals(4,tablero.getGrilla()[1][3].getValor());
	}
	
	@Test
	public void combinarIzquierdaTest() {
		tablero.moverIzquierda();		
//		4   |null|null|null|
//		4   |null|null|null|
//		8   |4   |2   |null|
//		4   |8   |null|null|
		
		assertEquals(4,tablero.getGrilla()[1][0].getValor());
	}
	
	@Test
	public void ganoTes() {
		tablero.agregar(0, 0, 2048);
		
		assertTrue(tablero.gano());
	}
	
	@Test
	public void tableroLlenoTest() {
		llenarTablero();
//		4|4|4|4|
//		2|2|2|4|
//		8|4|2|2|
//		2|4|2|8|
		
		assertTrue(tablero.tableroLleno());
	}

	@Test
	public void perdioTest() {
		llenarTablero();
		tablero.agregar(3, 1, 8);
//		4|2|8|4|
//		2|8|2|8|
//		8|4|8|2|
//		2|8|2|8|
		
		assertTrue(tablero.perdio());
	}
	
	public void llenarTablero() {
		tablero.agregar(0, 0, 4);
		tablero.agregar(0, 1, 2);
		tablero.agregar(0, 2, 8);
		tablero.agregar(1, 1, 8);
		tablero.agregar(1, 3, 8);
		tablero.agregar(2, 2, 8);
		tablero.agregar(3, 0, 2);
		tablero.agregar(3, 2, 2);
//		4|2|8|4|
//		2|8|2|8|
//		8|4|8|2|
//		2|4|2|8|
	}	
	@Test
	public void combinacionesPosiblesConTableroLlenoTestTrue() {
		llenarTablero();

		
//		4|2|8|4|
//		2|8|2|8|
//		8|4|8|2|
//		2|4|2|8|
		assertTrue(tablero.combinacionesPosiblesConTableroLleno());
	}

	@Test
	public void combinacionesPosiblesConTableroLlenoTestFalse() {
		llenarTablero();
		tablero.agregar(2, 1, 2);
		
//		4|2|8|4|
//		2|8|2|8|
//		8|4|8|2|
//		2|8|2|8|
		assertFalse(tablero.combinacionesPosiblesConTableroLleno());
	}
	



	
}

























