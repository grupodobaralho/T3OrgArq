package T3;

import java.util.ArrayList;

public class Cache {
	
	public static int TAM_BYTE = 8;
	public static int BINARIO = 2;
	public static String HIT = "HIT\t";
	public static String MISS = "MISS\t";

	private int bitsTag;
	private int bitsLinha;
	private int bitsPalavra;
	private int tLinha;
	private int tColuna;
	private String[] tag;
	private String[][] cache;
	
	private int countHit = 0;
	private int countMiss = 0;

	private ArrayList<String> relatorio;

	public Cache(int bitsTag, int bitsLinha, int bitsPalavra) {
		
		this.bitsTag = bitsTag;
		this.bitsLinha = bitsLinha;
		this.bitsPalavra = bitsPalavra;
		
		// Quantidade de linhas da Cache + vetor de tags
		this.tLinha = (int) Math.pow(2, bitsLinha);
		tag = new String[tLinha];
		
		// Quantidade de blocos (colunas)
		this.tColuna = (int) Math.pow(2, bitsPalavra);
		cache = new String[tLinha][tColuna];

		relatorio = new ArrayList<>();

	}

	// Mapeamento direto, com bitsTag bits para tag, bitsLinha bits para linha e bitsPalavra para palavra
	public void add(String endereco) {
		int linha = Integer.parseInt(endereco.substring(bitsTag, bitsTag + bitsLinha), BINARIO);
		int palavra = Integer.parseInt(endereco.substring(bitsTag + bitsLinha, TAM_BYTE), BINARIO);

		if (tag[linha] == null || !cache[linha][palavra].equals(endereco)) {
			relatorio.add(MISS);
			countMiss++;
			tag[linha] = endereco.substring(0, bitsTag);
			for (int i = 0; i < tColuna; i++) {
				cache[linha][i] = endereco.substring(0, TAM_BYTE - bitsPalavra) + decToBin(i);
			}
		} else {
			relatorio.add(HIT);
			countHit++;
		}
	}

	private String decToBin(int i) {
		String linha = Integer.toBinaryString(i);
		while (linha.length() < bitsPalavra )
			linha = "0" + linha;
		return linha;
	}


	public void printa() {
		System.out.printf("%-9s %-9s ", "Linha", "TAG");
		String[] colunas = {"  000  ", "  001  ", "  010  ", "  011  ", "  100  ", "  101  ", "  110  ", "  111  "};
		for(int i = 0; i<tColuna; i++)
			System.out.printf("%-9s ", colunas[i]);
		System.out.println("\n-----------------------------------------------------------------------------------------------");
		for (int i = 0; i < tLinha; i++) {
			String linha = decToBin(i);
			System.out.printf("%-10s", linha);
			System.out.printf("%-10s", tag[i]);
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
		System.out.println("Percentual de Hit: " + ((100*countHit)/total) + "%");
		System.out.println("Percentual de Miss: " + ((100*countMiss)/total) + "%");
	}

}
