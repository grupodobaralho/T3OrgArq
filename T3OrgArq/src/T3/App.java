package T3;

import java.io.IOException;

public class App {

	public static void main(String args[]) throws IOException {

		HexToBin converser = new HexToBin();

		String[] teste = converser.read();

		/*
		for (int i = 0; i < teste.length; i++) {
			if (teste[i] != null)
				System.out.println(teste[i]);

		}
		*/
		
		//ESTE FOI UM TESTE DE MESA QUE EU FIZ E FUNCIONOU!
		/*
		Cache cache = new Cache(4,8);
		System.out.printf("%-9s %-9s %-9s %-9s %-9s %-9s %-9s %-9s %-9s\n", "TAG", "000", "001", "010", "011", "100", "101", "110", "111");
		System.out.println("-----------------------------------------------------------------------------------------");
		cache.add("00101011");
		cache.add("00101011");
		cache.add("00101010");
		cache.add("11011111");
		cache.printa();
		*/
		
		//TESTANDO COM A LEITURA DO VINIBOY
		System.out.printf("%-9s %-9s %-9s %-9s %-9s %-9s %-9s %-9s %-9s\n", "TAG", "000", "001", "010", "011", "100", "101", "110", "111");
		System.out.println("-----------------------------------------------------------------------------------------");
		Cache cache1 = new Cache(4,8);
		for(int i=0; i<teste.length; i++) {
			cache1.add(teste[i]);
		}
		cache1.printa();
		
		System.out.println("\n####################################################################################################\n");
		System.out.printf("%-9s %-9s %-9s %-9s %-9s %-9s %-9s %-9s %-9s\n", "TAG", "000", "001", "010", "011", "100", "101", "110", "111");
		System.out.println("-----------------------------------------------------------------------------------------");
		Cache cache2 = new Cache(8,4);
		for(int i=0; i<teste.length; i++) {
			cache2.add(teste[i]);
		}
		cache2.printa();
		
		System.out.println("\n####################################################################################################\n");
		System.out.printf("%-9s %-9s %-9s %-9s %-9s %-9s %-9s %-9s %-9s\n", "TAG", "000", "001", "010", "011", "100", "101", "110", "111");
		System.out.println("-----------------------------------------------------------------------------------------");
		Cache cache3 = new Cache(16,2);
		for(int i=0; i<teste.length; i++) {
			cache3.add(teste[i]);
		}
		cache3.printa();
		
		
		
	}
}