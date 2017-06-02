package T3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HexToBin {

	public static String[] read() throws IOException {
		String bin = "";
		String binFragment = "";
		String[] retorno = new String[407];
		try {
			int cont = 0;
			BufferedReader br = new BufferedReader(new FileReader("Files/T3input.txt"));
			String number = "";
			while (br.ready()) {
				number = br.readLine();
				bin = "";
				binFragment = "";
				int iHex;
				number = number.trim();

				for (int i = 0; i < number.length(); i++) {
					iHex = Integer.parseInt("" + number.charAt(i), 16);
					binFragment = Integer.toBinaryString(iHex);

					while (binFragment.length() < 4) {
						binFragment = "0" + binFragment;
					}
					bin += binFragment;
					retorno[cont] = bin;
				}

				cont++;
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return retorno;
	}

}
