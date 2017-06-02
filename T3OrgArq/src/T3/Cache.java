package T3;

import java.util.ArrayList;

public class Cache {

	private String[][] cache;
	private int tLinha;
	private int tColuna;
	
	private int countHit = 0;
	private int countMiss = 0;

	private ArrayList<String> relatorio;

	public Cache(int qtLinhas, int qtBlocos) {

		// Quantidade de linhas da Cache
		this.tLinha = qtLinhas;
		// Quantidade de blocos (colunas) + a tag
		this.tColuna = qtBlocos + 1;
		cache = new String[tLinha][tColuna];

		relatorio = new ArrayList<>();

	}

	public void add(String endereco) {

		if (tLinha == 4 && tColuna == 9) {
			adicionaUm(endereco);
		} else if (tLinha == 8 && tColuna == 5) {
			adicionaDois(endereco);
		} else if (tLinha == 16 && tColuna == 3) {
			adicionaTres(endereco);
		} else {
			throw new RuntimeException("Erro no add da classe Cache!");
		}

	}

	// Mapeamento direto, com 3 bits para tag, 2 bits para linha e 3 bits
	// para palavra (cache com 4 linhas, 8 palavras por linha).
	public void adicionaUm(String endereco) {
		String tag = endereco.substring(0, 3);
		int linha = Integer.parseInt(endereco.substring(3, 5), 2);
		int palavra = Integer.parseInt(endereco.substring(5, 8), 2) + 1;

		if (cache[linha][0] == null || !cache[linha][0].equals(tag) || !cache[linha][palavra].equals(endereco)) {
			relatorio.add("Miss;");
			countMiss++;
			cache[linha][0] = tag;
			for (int i = 1; i < tColuna; i++) {
				switch (i) {
				case 1:
					cache[linha][i] = endereco.substring(0, 5) + "000";	break;
				case 2:
					cache[linha][i] = endereco.substring(0, 5) + "001";	break;
				case 3:
					cache[linha][i] = endereco.substring(0, 5) + "010";	break;
				case 4:
					cache[linha][i] = endereco.substring(0, 5) + "011";	break;
				case 5:
					cache[linha][i] = endereco.substring(0, 5) + "100";	break;
				case 6:
					cache[linha][i] = endereco.substring(0, 5) + "101";	break;
				case 7:
					cache[linha][i] = endereco.substring(0, 5) + "110";	break;
				case 8:
					cache[linha][i] = endereco.substring(0, 5) + "111";	break;
				default:
					throw new RuntimeException("Erro no switch case");
				}
			}
		} else {
			relatorio.add("HIT;");
			countHit++;
		}
	}

	//Mapeamento direto, com 3 bits para tag, 3 bits para linha e 2 bits
	//para palavra (cache com 8 linhas, 4 palavras por linha).
	private void adicionaDois(String endereco) {
		String tag = endereco.substring(0, 3);
		int linha = Integer.parseInt(endereco.substring(3, 6), 2);
		int palavra = Integer.parseInt(endereco.substring(6, 8), 2) + 1;

		if (cache[linha][0] == null || !cache[linha][0].equals(tag) || !cache[linha][palavra].equals(endereco)) {
			//System.out.println(cache[linha][0] + "==NULL || "+ tag + " end: "+cache[linha][palavra] +"==" + endereco );
			relatorio.add("Miss;");
			countMiss++;
			cache[linha][0] = tag;
			for (int i = 1; i < tColuna; i++) {
				switch (i) {
				case 1:
					cache[linha][i] = endereco.substring(0, 6) + "00";	break;
				case 2:
					cache[linha][i] = endereco.substring(0, 6) + "01";	break;
				case 3:
					cache[linha][i] = endereco.substring(0, 6) + "10";	break;
				case 4:
					cache[linha][i] = endereco.substring(0, 6) + "11";	break;				
				default:
					throw new RuntimeException("Erro no switch case");
				}
			}
		} else {
			relatorio.add("HIT;");
			countHit++;
		}
	}

	//Mapeamento direto, com 3 bits para tag, 4 bits para linha e 1 bit
	//para palavra (cache com 16 linhas, 2 palavras por linha).

	private void adicionaTres(String endereco) {
		String tag = endereco.substring(0, 3);
		int linha = Integer.parseInt(endereco.substring(3, 7), 2);
		int palavra = Integer.parseInt(endereco.substring(7, 8), 2) + 1;

		if (cache[linha][0] == null || !cache[linha][0].equals(tag) || !cache[linha][palavra].equals(endereco)) {
			relatorio.add("Miss;");
			countMiss++;
			cache[linha][0] = tag;
			for (int i = 1; i < tColuna; i++) {
				switch (i) {
				case 1:
					cache[linha][i] = endereco.substring(0, 6) + "00";	break;
				case 2:
					cache[linha][i] = endereco.substring(0, 6) + "01";	break;		
				default:
					throw new RuntimeException("Erro no switch case");
				}
			}
		} else {
			relatorio.add("HIT;");
			countHit++;
		}
	}

	public void printa() {
		for (int i = 0; i < tLinha; i++) {
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
