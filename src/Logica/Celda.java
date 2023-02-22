package Logica;

public class Celda 
{
	
	private Integer valor;
	
	public Celda() 
	{
	
	}
	
	public Celda(int valor) {
		this.valor = valor;
	}

	public int getValor()  
	{
		return this.valor;
	}

	public void setValor(Integer valor) 
	{
		this.valor = valor;
	}
	
	public String toString() {
		StringBuilder cadena= new StringBuilder();
		return cadena.append(valor).toString();
	}


	
}
