package T3;

import java.util.ArrayList;

public class Cache {

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

	// Mapeamento direto, com 3 bits para tag, 2 bits para linha e 3 bits
	// para palavra (cache com 4 linhas, 8 palavras por linha).
	public void adicionaUm(String endereco) {
		int linha = Integer.parseInt(endereco.substring(3, 5), 2);
		int palavra = Integer.parseInt(endereco.substring(5, 8), 2);

		if (tag[linha] == null || !cache[linha][palavra].equals(endereco)) {
			relatorio.add("MISS\t");
			countMiss++;
			tag[linha] = endereco.substring(0, 3);
			for (int i = 0; i < tColuna; i++) {
				switch (i) {
				case 0:
					cache[linha][i] = endereco.substring(0, 5) + "000";	break;
				case 1:
					cache[linha][i] = endereco.substring(0, 5) + "001";	break;
				case 2:
					cache[linha][i] = endereco.substring(0, 5) + "010";	break;
				case 3:
					cache[linha][i] = endereco.substring(0, 5) + "011";	break;
				case 4:
					cache[linha][i] = endereco.substring(0, 5) + "100";	break;
				case 5:
					cache[linha][i] = endereco.substring(0, 5) + "101";	break;
				case 6:
					cache[linha][i] = endereco.substring(0, 5) + "110";	break;
				case 7:
					cache[linha][i] = endereco.substring(0, 5) + "111";	break;
				default:
					throw new RuntimeException("Erro no switch case");
				}
			}
		} else {
			relatorio.add("HIT\t");
			countHit++;
		}
	}

	//Mapeamento direto, com 3 bits para tag, 3 bits para linha e 2 bits
	//para palavra (cache com 8 linhas, 4 palavras por linha).
	private void adicionaDois(String endereco) {
		int linha = Integer.parseInt(endereco.substring(3, 6), 2);
		int palavra = Integer.parseInt(endereco.substring(6, 8), 2);

		if (tag[linha] == null || !cache[linha][palavra].equals(endereco)) {
			//System.out.println(cache[linha][0] + "==NULL || "+ tag + " end: "+cache[linha][palavra] +"==" + endereco );
			relatorio.add("MISS\t");
			countMiss++;
			tag[linha] = endereco.substring(0, 3);
			for (int i = 0; i < tColuna; i++) {
				switch (i) {
				case 0:
					cache[linha][i] = endereco.substring(0, 6) + "00";	break;
				case 1:
					cache[linha][i] = endereco.substring(0, 6) + "01";	break;
				case 2:
					cache[linha][i] = endereco.substring(0, 6) + "10";	break;
				case 3:
					cache[linha][i] = endereco.substring(0, 6) + "11";	break;				
				default:
					throw new RuntimeException("Erro no switch case");
				}
			}
		} else {
			relatorio.add("HIT\t");
			countHit++;
		}
	}

	//Mapeamento direto, com 3 bits para tag, 4 bits para linha e 1 bit
	//para palavra (cache com 16 linhas, 2 palavras por linha).

	private void adicionaTres(String endereco) {
		int linha = Integer.parseInt(endereco.substring(3, 7), 2);
		int palavra = Integer.parseInt(endereco.substring(7, 8), 2);

		if (tag[linha] == null || !cache[linha][palavra].equals(endereco)) {
			relatorio.add("MISS\t");
			countMiss++;
			tag[linha] = endereco.substring(0, 3);
			for (int i = 0; i < tColuna; i++) {
				switch (i) {
				case 0:
					cache[linha][i] = endereco.substring(0, 7) + "0";	break;
				case 1:
					cache[linha][i] = endereco.substring(0, 7) + "1";	break;		
				default:
					throw new RuntimeException("Erro no switch case");
				}
			}
		} else {
			relatorio.add("HIT\t");
			countHit++;
		}
	}

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
		System.out.println("Percentual de Miss: " + ((100*countMiss)/total) + "%");
		System.out.println("Percentual de Hit: " + ((100*countHit)/total) + "%");
	}

}
