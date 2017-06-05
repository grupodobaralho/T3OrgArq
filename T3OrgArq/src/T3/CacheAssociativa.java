package T3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class CacheAssociativa {
	
	public class MemoriaAssociativa {
		LinkedHashMap<Integer, Integer> mem;

		public MemoriaAssociativa() {
			mem = new LinkedHashMap<>();
		}

		// procura se existe tag na memAssociativa, se não tiver, retorna -1
		// se tiver, retorna a posição da cache
		public int procura(int tag) {
			if(mem.containsKey(tag)==false)
				return -1;
			else return mem.get(tag);
		}		
			
	}

	private int bitsLinha;
	private int bitsPalavra;
	private int tLinha;
	private int tColuna;
	private String[][] cache;
	private MemoriaAssociativa memoriaAss = new MemoriaAssociativa();
	
	private int countHit = 0;
	private int countMiss = 0;
	
	private ArrayList<String> relatorio;
	
	private int contador = 0;


	public CacheAssociativa(int bitsLinha, int bitsPalavra) {
			
		this.bitsLinha = bitsLinha;
		this.bitsPalavra = bitsPalavra;
		
		// Quantidade de linhas da Cache
		this.tLinha = (int) Math.pow(2, bitsLinha);	
		// Quantidade de blocos (colunas)
		this.tColuna = (int) Math.pow(2, bitsPalavra);
		
		cache = new String[tLinha][tColuna];

		relatorio = new ArrayList<>();
	}
	
	public void add(String endereco) {

		if (tLinha == 4 && tColuna == 8) {
			adicionaUm(endereco);
		} else if (tLinha == 8 && tColuna == 4) {
			adicionaDois(endereco);
		} else if (tLinha == 16 && tColuna == 2) {
			adicionaTres(endereco);
		} else {
			throw new RuntimeException("Erro no add da classe Cache!");
		}

	}
	
	// Mapeamento associativo, com 5 bits para a tag e 3 bits para palavra
	// (cache com 4 linhas, 8 palavras por linha).
	public void adicionaUm(String endereco){
		int tag = Integer.parseInt(endereco.substring(0, 5), 2);
		//int palavra = Integer.parseInt(endereco.substring(5, 8), 2);
		
		if(memoriaAss.procura(tag) == -1){			
			relatorio.add("MISS\t");
			countMiss++;
			System.out.println("z");
			memoriaAss.mem.put(tag, contador);
			contador++;
			for (int i = 0; i < tColuna; i++) {
				switch (i) {
				case 0:
					cache[memoriaAss.mem.get(tag)][i] = "000";  break;
				case 1:
					cache[memoriaAss.mem.get(tag)][i] = "001";	break;
				case 2:
					cache[memoriaAss.mem.get(tag)][i] = "010";	break;
				case 3:
					cache[memoriaAss.mem.get(tag)][i] = "011";	break;
				case 4:
					cache[memoriaAss.mem.get(tag)][i] = "100";	break;
				case 5:
					cache[memoriaAss.mem.get(tag)][i] = "101";	break;
				case 6:
					cache[memoriaAss.mem.get(tag)][i] = "110";	break;
				case 7:
					cache[memoriaAss.mem.get(tag)][i] = "111";	break;
				default:
					throw new RuntimeException("Erro no switch case");
				}
			}
		} else {
			relatorio.add("HIT\t");
			countHit++;
		}		
	}
	
	// Mapeamento associativo, com 6 bits para tag e 2 bits para palavra
	// (cache com 8 linhas, 4 palavras por linha).
	public void adicionaDois(String endereco){
		int tag = Integer.parseInt(endereco.substring(0, 6), 2);
		//int palavra = Integer.parseInt(endereco.substring(6, 8), 2);
		
		if(memoriaAss.procura(tag) == -1){			
			relatorio.add("MISS\t");
			countMiss++;
			System.out.println("z");
			memoriaAss.mem.put(tag, contador);
			contador++;
			for (int i = 0; i < tColuna; i++) {
				switch (i) {
				case 0:
					cache[memoriaAss.mem.get(tag)][i] = "00"; break;
				case 1:
					cache[memoriaAss.mem.get(tag)][i] = "01"; break;
				case 2:
					cache[memoriaAss.mem.get(tag)][i] = "10"; break;
				case 3:
					cache[memoriaAss.mem.get(tag)][i] = "11"; break;
				default:
					throw new RuntimeException("Erro no switch case");
				}
			}
		} else {
			relatorio.add("HIT\t");
			countHit++;
		}	
		
	}
	
	// Mapeamento associativo, com 7 bits para tag e 1 bit para palavra
	// (cache com 16 linhas, 2 palavras por linha).
	public void adicionaTres(String endereco){		
		int tag = Integer.parseInt(endereco.substring(0, 7), 2);
		//int palavra = Integer.parseInt(endereco.substring(7, 8), 2);
		
		if(memoriaAss.procura(tag) == -1){			
			relatorio.add("MISS\t");
			countMiss++;
			System.out.println("z");
			memoriaAss.mem.put(tag, contador);
			contador++;
			for (int i = 0; i < tColuna; i++) {
				switch (i) {
				case 0:
					cache[memoriaAss.mem.get(tag)][i] = "0"; break;
				case 1:
					cache[memoriaAss.mem.get(tag)][i] = "1"; break;
				default:
					throw new RuntimeException("Erro no switch case");
				}
			}
		} else {
			relatorio.add("HIT\t");
			countHit++;
		}
		
	}
	/*
	public void printa() {
		System.out.printf("%-9s %-9s ", "Linha", "TAG");
		String[] colunas = {"  000  ", "  001  ", "  010  ", "  011  ", "  100  ", "  101  ", "  110  ", "  111  "};
		for(int i = 0; i<tColuna; i++)
			System.out.printf("%-9s ", colunas[i]);
		System.out.println("\n-----------------------------------------------------------------------------------------------");
		for (int i = 0; i < tLinha; i++) {
			String linha = Integer.toBinaryString(i);
			while (linha.length() < bitsLinha )
				linha = "0" + linha;
			System.out.printf("%-10s", linha);
			System.out.printf("%-10s", memoriaAss.mem.keySet());
			Set hs = memoriaAss.mem.keySet();		
			for (int j = 0; j < tColuna; j++) {
				if (cache[i][j] == null) {
					System.out.printf("%-10s", "NULL ");
				} else {
					System.out.printf("%-10s", cache[i][j] + " ");
				}

			}
			System.out.println();
		}

		System.out.println();
		int count = 0;
		for (String str : relatorio) {
			if (count == 15) {
				System.out.println();
				count = 0;
			}
			System.out.printf(str);
			count++;
		}
		float total = countHit + countMiss;
		System.out.println('\n');
		System.out.println("Percentual de Miss: " + ((100*countMiss)/total) + "%");
		System.out.println("Percentual de Hit: " + ((100*countHit)/total) + "%");
	}
	*/

}
