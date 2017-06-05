package T3;

import java.io.IOException;

public class App {

	public static void main(String args[]) throws IOException {

		String[] teste = HexToBin.read();
		
		//Cache(int bitsTag, int bitsLinha, int bitsPalavra)
		Cache cache1 = new Cache(3,2,3);
		for(int i=0; i<teste.length; i++) {
			cache1.add(teste[i]);
		}
		cache1.printa();
		
		System.out.println("\n\n\n");
		Cache cache2 = new Cache(3,3,2);
		for(int i=0; i<teste.length; i++) {
			cache2.add(teste[i]);
		}
		cache2.printa();
		
		System.out.println("\n\n\n");
		Cache cache3 = new Cache(3,4,1);
		for(int i=0; i<teste.length; i++) {
			cache3.add(teste[i]);
		}
		cache3.printa();
		
		
		
	}
}