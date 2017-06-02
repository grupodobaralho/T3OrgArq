package T3;

public class HexToBin {

	
	StringBuffer str = new StringBuffer();
	
	public String hexToBin(String hex) {
		String bin = "";
		String binFragment = "";
		int iHex;
		hex = hex.trim();

		for (int i = 0; i < hex.length(); i++) {
			iHex = Integer.parseInt("" + hex.charAt(i), 16);
			binFragment = Integer.toBinaryString(iHex);

			while (binFragment.length() < 4) {
				binFragment = "0" + binFragment;
			}
			bin += binFragment;
		}
		return bin;

	}

}
