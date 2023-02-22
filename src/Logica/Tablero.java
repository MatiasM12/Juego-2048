package Logica;

import java.util.Random;


public class Tablero 
{

	private Celda[][] grilla;
	private int puntos;    


	public Tablero(int tamano) 
	{
		this.grilla = new Celda[tamano][tamano];

		//genera dos numeros random apenas inicializa el tablero
		numRandom();
		numRandom();
	}



	//-------------------------------MOVIMIENTOS---------------------------------	


	public void  moverArriba() 
	{

		for(int j=0; j< grilla.length; j++) 
		{
			combinarArriba(j);
			for(int i=0; i<grilla.length;i++) 
			{
				Celda actual=grilla[i][j];
				if( i<grilla.length-1) 
				{
					Celda siguiente=grilla[i+1][j];
					if (celdaLibre(actual) && !celdaLibre(siguiente) ) 
					{
						grilla[i][j]= grilla[i+1][j]; 
						grilla[i+1][j] = null;
					}
					if(i>0)
					{	
						Celda anterior=grilla[i-1][j];
						if (celdaLibre(anterior))
						{
							grilla[i-1][j] = grilla[i][j];
							grilla[i][j] = null;
						}
					}
				}
				if(celdaLibre(grilla [0][j])) 
					moverUltimoArriba(j);
			}	
		}		
		if(!tableroLleno())
			numRandom();
	}

	public void  moverAbajo() 
	{
		for(int j=0; j< grilla.length; j++) 
		{
			combinarAbajo(j);
			for(int i=grilla.length-1; i>0;i--) 
			{
				Celda actual=grilla[i][j];
				if(  i<grilla.length-1) 
				{
					Celda siguiente=grilla[i-1][j];
					if (celdaLibre(actual) && !celdaLibre(siguiente) ) 
					{
						grilla[i][j]= grilla[i-1][j]; 
						grilla[i-1][j] = null;
					}
					if(i>0) 
					{	
						Celda anterior=grilla[i+1][j];
						if (celdaLibre(anterior))
						{
							grilla[i+1][j] = grilla[i][j];
							grilla[i][j] = null;
						}
					}
				}
				if(celdaLibre(grilla [grilla.length-1][j])) 
					moverUltimoAbajo(j);
			}
		}		
		if (!tableroLleno())
			numRandom();

	}

	public void  moverDerecha() 
	{
		for(int i=0; i< grilla.length; i++) 
		{
			combinarDerecha(i);
			for(int j=grilla.length-1; j>0;j--) 
			{
				Celda actual=grilla[i][j];
				if(j<grilla.length-1) 
				{
					Celda siguiente=grilla[i][j-1];
					if (celdaLibre(actual) && !celdaLibre(siguiente) ) 
					{
						grilla[i][j]= grilla[i][j-1]; 
						grilla[i][j-1] = null;
					}
					if(j > 0) 
					{	
						Celda anterior=grilla[i][j+1];
						if (celdaLibre(anterior))
						{
							grilla[i][j+1] = grilla[i][j];
							grilla[i][j] = null; 
						}
					}
				}
				if(celdaLibre(grilla [i][grilla.length-1])) 
					moverUltimoDerecha(i);
			}
		}			
		if(!tableroLleno())
			numRandom();

	}

	public void  moverIzquierda() 
	{
		for(int i=0; i< grilla.length; i++) 
		{
			combinarIzquierda(i);
			for(int j=0; j<grilla.length-1;j++) 
			{
				Celda actual=grilla[i][j];

				if(j<grilla.length-1) 
				{
					Celda siguiente=grilla[i][j+1];
					if (celdaLibre(actual) && !celdaLibre(siguiente) ) 
					{
						grilla[i][j]= grilla[i][j+1]; 
						grilla[i][j+1] = null;
					}
					if(j > 0) 
					{	
						Celda anterior=grilla[i][j-1];
						if (celdaLibre(anterior))
						{
							grilla[i][j-1] = grilla[i][j];
							grilla[i][j] = null;
						}
					}
				}
				if(celdaLibre(grilla [i][0])) 
					moverUltimoIzquierda(i);
			}

		}			
		if(!tableroLleno())
			numRandom();

	}

	//-----------------------METODOS GANÓ Y PERDIÓ-----------------------------------------

	public boolean gano() {
		for (int i=0; i<grilla.length; i++){
			for (int j=0; j<grilla.length; j++){
				if(grilla[i][j] != null && grilla[i][j].getValor() == 2048)
					return true;
			}
		}
		return false;
	}


	
	public boolean perdio() 
	{
		return ((!combinacionesPosiblesConTableroLleno()) && tableroLleno());
	}



	//------------------------METODOS AUXILIARES-----------------------------------------


	private void combinarArriba( int j) {
		for(int i=0; i<grilla.length;i++) 
		{
			int borde=grilla.length-1;
			int indice=1;
			Celda actual = grilla[i][j];
			if(!celdaLibre(actual) ) 
			{
				combinarArribaAux(i,j,actual,indice,borde);

			}
		}	

	}

	private void combinarArribaAux(int i, int j, Celda actual, int indice, int borde) {
		if(i<borde) {
			Celda siguiente = grilla[i+indice][j];
			if(!celdaLibre(siguiente) && celdasIguales(actual,siguiente)) 
			{
				combina(actual,siguiente);   
				grilla[i+indice][j]=null; 
			}
			else if( celdaLibre(siguiente))
			{
				siguiente = grilla[i+indice][j];
				combinarArribaAux(i+1,j,actual,indice++,borde--);
			}
		}

	}

	private void combinarAbajo(int j) {
		for(int i=grilla.length-1; i>0;i--) 
		{
			int borde=0;
			int indice=1;
			Celda actual = grilla[i][j];
			if(!celdaLibre(actual) ) 
			{
				combinarAbajoAux(i,j,actual,indice,borde);

			}
		}	

	}

	private void combinarAbajoAux(int i, int j, Celda actual, int indice, int borde) {
		if(i>borde) 
		{
			Celda siguiente = grilla[i-indice][j];
			if(!celdaLibre(siguiente) && celdasIguales(actual,siguiente)) 
			{
				combina(actual,siguiente);   
				grilla[i-indice][j]=null;  
			}
			else if( celdaLibre(siguiente))
			{
				siguiente = grilla[i-indice][j];
				combinarAbajoAux(i-1,j,actual,indice++,borde++);
			}
		}
	}

	private void combinarDerecha(int j) {
		for(int i=grilla.length-1; i>0;i--) 
		{
			int borde=0;
			int indice=1;
			Celda actual = grilla[j][i];
			if(!celdaLibre(actual) ) 
			{
				combinarDerechaAux(j,i,actual,indice,borde);

			}
		}	

	}

	private void combinarDerechaAux(int i, int j, Celda actual, int indice, int borde) {
		if(j>borde) {
			Celda siguiente = grilla[i][j-indice];
			if(!celdaLibre(siguiente) && celdasIguales(actual,siguiente)) 
			{
				combina(actual,siguiente);   
				grilla[i][j-indice]=null;  
			}
			else if( celdaLibre(siguiente))
			{
				siguiente = grilla[i][j-indice];
				combinarDerechaAux(i,j-1,actual,indice++,borde++);
			}
		}
	}

	private void combinarIzquierda(int j) 
	{
		for(int i=0; i<grilla.length;i++) 
		{
			int borde=grilla.length-1;
			int indice=1;
			Celda actual = grilla[j][i];
			if(!celdaLibre(actual) ) 
			{
				combinarIzquierdaAux(j,i,actual,indice,borde);

			}

		}

	}

	private void combinarIzquierdaAux(int i, int j, Celda actual, int indice, int borde) {
		if(j<borde) {
			Celda siguiente = grilla[i][j+indice];
			if(!celdaLibre(siguiente) && celdasIguales(actual,siguiente)) 
			{
				combina(actual,siguiente);   
				grilla[i][j+indice]=null;  
			}
			else if(celdaLibre(siguiente))
			{ 
				siguiente = grilla[i][j+indice];
				combinarIzquierdaAux(i,j+1,actual,indice++,borde--);
			}
		}

	}


	private void moverUltimoAbajo(int j) 
	{
		for(int i=grilla.length-1; i>0;i--) 
		{
			grilla[i][j]= grilla[i-1][j]; 
			grilla[i-1][j] = null;
		}
	}

	private void moverUltimoArriba(int j) 
	{
		for(int i=0; i<grilla.length-1;i++) 
		{
			grilla[i][j]= grilla[i+1][j]; 
			grilla[i+1][j] = null;
		}

	}

	private void moverUltimoDerecha(int i) 
	{
		for(int j=grilla.length-1; j>0;j--) 
		{
			grilla[i][j]= grilla[i][j-1]; 
			grilla[i][j-1] = null;
		}
	}

	private void moverUltimoIzquierda(int j) 
	{
		for(int i=0; i<grilla.length-1;i++) 
		{
			grilla[j][i]= grilla[j][i+1]; 
			grilla[j][i+1] = null;
		}
	}


	private boolean celdasIguales(Celda celda1,Celda celda2) 
	{
		if(celda1 != null && celda2 != null && celda1.getValor()==celda2.getValor()) 
		{
			return true;
		}
		return false;
	}

	private boolean celdaLibre(Celda celda) 
	{
		if(celda == null|| celda.getValor() == 0)
			return true;
		return false;
	}

	private void combina(Celda celda1,Celda celda2) // le agregue puntos para que vaya sumando
	{
		celda1.setValor(celda1.getValor()+celda2.getValor());
		celda2.setValor(null);
		setPuntos(getPuntos()+celda1.getValor());

	}
	


	public boolean combinacionesPosiblesConTableroLleno() {
		boolean ret = false;
		int tamanio=grilla.length;
		for(int i=0; i<tamanio; i++	) 
		{
			for (int j=0; j<tamanio; j++) 
			{
				if(grilla[i][j] != null) 
				{
					ret = ret || (i>0 && celdasIguales(grilla[i][j], grilla[i-1][j]));
					ret = ret || (i<tamanio-1 && celdasIguales(grilla[i][j], grilla[i+1][j]));
					ret = ret || (j>0 && celdasIguales(grilla[i][j], grilla[i][j-1]));
					ret = ret || (j<tamanio-1 && celdasIguales(grilla[i][j], grilla[i][j+1]));
				}
			}
		}
		return ret;
	}
	
	//--------------------RANDOMS -----------------------------------------------------------------



	public void numRandom()  //agrega el numero random a la grilla en una posicion random
	{
		int i = posicionRandom();
		int j = posicionRandom();
		while (!celdaLibre(this.grilla[i][j])) {
			i = posicionRandom();
			j = posicionRandom();
		}
		this.grilla[i][j] = generarNumero();

	}

	private int posicionRandom() // 
	{
		Random pos= new Random();
		int posicion= pos.nextInt(4);
		return posicion;
	}

	public Celda generarNumero() 
	{
		Random numero= new Random();
		int valorCelda= numero.nextInt(2);
		if(valorCelda==0) 
			return new Celda(2);
		else 
			return new Celda(4);

	}



	//---------------------------------METODOS PARA TEST --------------------------

	public Tablero() 
	{
		this.grilla = new Celda[4][4];
	}

	public void agregar(int posicionI,int posicionJ,Integer valor)
	{
		grilla[posicionI][posicionJ]=new Celda(valor);
	}


	//------------------------------TABLERO LLENO Y TABLERO VACIO -----------------------------


	public boolean tableroLleno() 
	{
		boolean ret = true;
		for (int i=0; i<grilla.length; i++)
		{
			for (int j=0; j<grilla.length; j++)
			{
				ret = ret && !celdaLibre(grilla[i][j]);
			}
		}
		return ret;
	}

	public boolean tableroVacio() 
	{
		boolean ret = true;
		for (int i=0; i<4; i++)
		{
			for (int j=0; j<4; j++)
			{
				ret = ret && !celdaLibre(grilla[i][j]);
			}
		}
		return ret;
	}


	//--------------------------------- GETTERS Y SETTERS  --------------------------


	public Celda[][] getGrilla() 
	{
		return grilla;
	}

	public void setGrilla(Celda[][] grilla) 
	{
		this.grilla = grilla;
	}

	public String getValor(int i, int j)
	{
		return grilla[i][j].toString(); 
	}

	public int getPuntos() 
	{
		return puntos;
	}

	public void setPuntos(int puntos) 
	{
		this.puntos = puntos;
	}

	//----------------------------------------- TO STRING DE GRILLA----------------------------------------


	@Override
	public String toString()
	{
		StringBuilder cadena= new StringBuilder();
		for(int i=0;i<grilla.length;i++)
		{
			for(int j=0;j<grilla.length;j++)
			{
				if(j==0) 
				{
					cadena.append("\n");
				}
				cadena.append(grilla[i][j]).append('|');

			}
		}
		return cadena.toString();
	}

}
