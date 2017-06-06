package T3;

import java.io.IOException;

public class App {

	public static void main(String args[]) throws IOException {

		String[] teste = HexToBin.read();

		System.out.println("\n--------------------------------MAPEAMENTO DIRETO--------------------------------\n");
		
		System.out.println("\nMAPEAMENTO DIRETO com 3 bits para tag, 2 bits para linha e 3 bits para palavra\n");
		Cache cache1 = new Cache(3,2,3);
		for(int i=0; i<teste.length; i++) {
			cache1.add(teste[i]);
		}
		cache1.printa();
		
		System.out.println("\n\n\n");
		System.out.println("\nMAPEAMENTO DIRETO com 3 bits para tag, 3 bits para linha e 2 bits para palavra\n");
		Cache cache2 = new Cache(3,3,2);
		for(int i=0; i<teste.length; i++) {
			cache2.add(teste[i]);
		}
		cache2.printa();
		
		System.out.println("\n\n\n");
		System.out.println("\nMAPEAMENTO DIRETO com 3 bits para tag, 4 bits para linha e 1 bit para palavra\n");
		Cache cache3 = new Cache(3,4,1);
		for(int i=0; i<teste.length; i++) {
			cache3.add(teste[i]);
		}
		cache3.printa();
		
		
		
		System.out.println("\n\n\n--------------------------------MAPEAMENTO ASSOCIATIVO--------------------------------\n");
		
		System.out.println("\nMAPEAMENTO ASSOCIATIVO com 5 bits para tag e 3 bits para palavra (cache com 4 linhas)\n");
		CacheAssociativa cache4 = new CacheAssociativa(5,4,3);
		for(int i=0; i<teste.length; i++) {
			cache4.add(teste[i]);
		}
		cache4.printa();
		
		System.out.println("\n\n\n");
		System.out.println("\nMAPEAMENTO ASSOCIATIVO com 6 bits para tag e 2 bits para palavra (cache com 8 linhas)\n");
		CacheAssociativa cache5 = new CacheAssociativa(6,8,2);
		for(int i=0; i<teste.length; i++) {
			cache5.add(teste[i]);
		}
		cache5.printa();
		
		System.out.println("\n\n\n");
		System.out.println("\nMAPEAMENTO ASSOCIATIVO com 7 bits para tag e 1 bit para palavra (cache com 16 linhas)\n");
		CacheAssociativa cache6 = new CacheAssociativa(7,16,1);
		for(int i=0; i<teste.length; i++) {
			cache6.add(teste[i]);
		}
		cache6.printa();
		
		
	}
}