package T3;

import java.util.ArrayList;

public class Cache {
		
	String[][] cache;	
	int tLinha;
	int tColuna;
	
	ArrayList<String> relatorio;
	
	
	public Cache(int qtLinhas, int qtBlocos){
		
		//Quantidade de linhas da Cache
		this.tLinha = qtLinhas;
		//Quantidade de blocos (colunas) + a tag
		this.tColuna = qtBlocos + 1;
		cache = new String[tLinha][tColuna];
		
		relatorio = new ArrayList<>();
		
	}
	
	
	
	

}
