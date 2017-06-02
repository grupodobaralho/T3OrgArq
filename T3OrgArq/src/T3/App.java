package T3;

import java.io.IOException;

public class App {

	public static void main(String args[]) throws IOException {

		HexToBin converser = new HexToBin();

		String[] teste = converser.read();

		for (int i = 0; i < teste.length; i++) {
			if (teste[i] != null)
				System.out.println(teste[i]);

		}

	}
}