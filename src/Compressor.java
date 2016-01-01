import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Compressor {

	HashMap<String, Integer> table = new HashMap<>();
	String sourceFile;
	String destinationFile;
	int nextVal = 0;

	/**
	 * Constructor for the compressor class.
	 * 
	 * @param originalFile
	 *            : Name of the file to be compressed
	 * @param compressedFile
	 *            : Name of the zipped file
	 */
	public Compressor(String originalFile, String compressedFile) {

		this.sourceFile = originalFile;
		this.destinationFile = compressedFile;

		// Enter all symbols in the table
		for (char ch = 0; ch <= 255; ch++) {
			table.put(ch + "", nextVal);

			//	System.out.println((ch + "") + nextVal);
			
			nextVal++;
		}
	}

	/**
	 * Initiate compression
	 * 
	 * @throws IOException
	 */
	public void startCompressing() throws IOException {

		char c;
		String s = null;

		int value;

		DataInputStream in = null;
		DataOutputStream out = null;
		Buffer12BitWriterClass bwc = null;
		try {
			in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(sourceFile)));

			out = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(destinationFile)));

			bwc = new Buffer12BitWriterClass(out);

			// s = ((char) (in.readByte() & 0x00FF) + "");

			// This one below will also work

			byte b1 = in.readByte();
		
			/*	System.out.println(Integer.toHexString(b1 & 0x00FF) + "   "
					+ Integer.toBinaryString(b1 & 0x00FF));*/
			
			int temp = (char) b1;
			s = ((char) (temp & 0x00FF)) + "";

			while (true) {

				byte b2 = in.readByte();
			
				/*System.out.println(Integer.toHexString(b2 & 0x00FF) + "   "
						+ Integer.toBinaryString(b2 & 0x00FF));*/
				
				c = (char) (b2 & 0x00FF);
				
				// c = (char) (in.readByte() & 0x00FF);

				if (table.containsKey(s + c)) {
					s = s + c;
				} else {
					value = table.get(s);
					bwc.writeData(value);

					insertIntoTable(s + c, nextVal);
					s = c + "";
				}
			}
		} catch (Exception e) {
			in.close();
		}
		value = table.get(s);
		bwc.writeData(value);
		bwc.flush();
		out.flush();
		out.close();
	}

	/**
	 * Table insertion is not done in this as it is normally done. *
	 * 
	 * @param key
	 * @param value
	 */
	public void insertIntoTable(String key, int value) {

		if (value >= 4096) {
			// Array is full
			table = new HashMap<>();
			nextVal = 0;

			for (char ch = 0; ch <= 255; ch++) {
				table.put(ch + "", nextVal);
				nextVal++;
			}

		}
		table.put(key, nextVal);
		nextVal++;

	}

	public static void main(String[] args) throws Exception {

		
		 Compressor comp = new Compressor("access_log_Jul95.txt",
		 "zippedlog.txt"); comp.startCompressing(); DeCompressor deComp =
		 new DeCompressor("zippedlog.txt", "unzippedlog.txt");
		 deComp.startDecompressing();
		

		/*
		 * Compressor comp = new Compressor("words.html", "zippedWords.html");
		 * comp.startCompressing();
		 * 
		 * DeCompressor deComp = new DeCompressor("zippedWords.html",
		 * "unzippedWords.html"); deComp.startDecompressing();
		 */

		/*Compressor comp = new Compressor("shortwords.txt",
				"zippedShortWords.txt");
		comp.startCompressing();
		DeCompressor deComp = new DeCompressor("zippedShortWords.txt",
				"unzippedShortWords.txt");
		deComp.startDecompressing();*/
		
		/*
		 * Compressor comp = new Compressor("CrimeLatLonXY1990.csv",
		 * "zippedwords.csv"); comp.startCompressing(); DeCompressor deComp =
		 * new DeCompressor("zippedwords.csv", "unzippedwords.csv");
		 * deComp.startDecompressing();
		 */

	}
}