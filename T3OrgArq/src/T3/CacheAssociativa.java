package T3;

import java.util.ArrayList;

public class CacheAssociativa {
	
	public static int TAM_BYTE = 8;
	public static int BINARIO = 2;
	public static String HIT = "HIT\t";
	public static String MISS = "MISS\t";
	
	private int bitsTag;
	private int linhas;
	private int bitsPalavra;
	private int colunas;
	private String[] memAss;
	private String[][] cache;
	private int countHit = 0;
	private int countMiss = 0;
	private int contador = 0;
	private ArrayList<String> relatorio;

	public CacheAssociativa(int bitsTag, int linhas, int bitsPalavra) {
		
		this.bitsTag = bitsTag;
		this.bitsPalavra = bitsPalavra;
		
		// Quantidade de linhas e colunas da Cache + vetor de tags
		this.linhas = linhas;
		this.colunas = (int) Math.pow(2, bitsPalavra);
		memAss = new String[linhas];
		
		// Instanciação da cache e do relatório
		cache = new String[linhas][colunas];
		relatorio = new ArrayList<>();
	}
	
	// Mapeamento associativo, com bitsTag bits para tag, linhas para linha e bitsPalavra para palavra
	public void add(String endereco) {
		
		boolean miss = true;
		
		for(int i=0; i<memAss.length; i++) {
			if(memAss[i] != null && memAss[i].equals(endereco.substring(0, bitsTag))) {
				relatorio.add(HIT);
				countHit++;
				miss = false;
				break;
			}
		}
		
		if(miss) {
			memAss[(contador++)%linhas] = endereco.substring(0, bitsTag);
			relatorio.add(MISS);
			countMiss++;
		}
	}

	private String decToBin(int i, int bits) {
		String linha = Integer.toBinaryString(i);
		while (linha.length() < bits )
			linha = "0" + linha;
		return linha;
	}


	public void printa() {
		System.out.printf("%-9s %-9s ", "Linha", "TAG");
		
		for(int i = 0; i<colunas; i++)
			System.out.printf("%-9s ", decToBin(i, bitsPalavra));
		System.out.println("\n-----------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < linhas; i++) {
			String linha = decToBin(i, (bitsTag-3));
			System.out.printf("%-10s", linha);
			System.out.printf("%-10s", memAss[i]);
			
			if (memAss[i] != null) {
				for (int j = 0; j < colunas; j++) {
					System.out.printf("%-10s", decToBin(j, bitsPalavra));
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
		System.out.println("Percentual de Hit: " + ((100*countHit)/total) + "%");
		System.out.println("Percentual de Miss: " + ((100*countMiss)/total) + "%");
	}

}
