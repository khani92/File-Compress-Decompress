import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeCompressor {

	String[] indexedTable = new String[(int) Math.pow(2, 12)];
	String zippedFile;
	String unzippedFile;
	int nextLoc = 0;

	/**
	 * Constructor for decompressing
	 * 
	 * @param zippedFile
	 *            : Name of the zipped file
	 * @param unzippedFile
	 *            : Name of the output file. This is the final decompressed file
	 */
	public DeCompressor(String zippedFile, String unzippedFile) {

		this.zippedFile = zippedFile;
		this.unzippedFile = unzippedFile;

		for (int i = 0; i < 256; i++) {
			indexedTable[nextLoc] = ((char) (i)) + "";

			//System.out.println(nextLoc + "  " + indexedTable[nextLoc]);
			
			nextLoc++;
		}
	}

	/**
	 * Initiate decompressing
	 * 
	 * @throws IOException
	 */
	public void startDecompressing() throws IOException {

		DataInputStream in = null;
		DataOutputStream out = null;
		BufferCharWriterClass bcw = null;
		List<Integer> codesList = new ArrayList<>();

		try {
			in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(zippedFile)));

			bcw = new BufferCharWriterClass();

			out = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(unzippedFile)));

			while (true) {
				// read the entire file and get all the numerical code words in
				// the array list
				byte data = in.readByte();
				bcw.writeData(data, codesList);
			}

		} catch (Exception e) {
			in.close();
		}
		bcw.flushToFile(codesList);

		// By now all the binary data from compressed file is read and its
		// corresponding numeric code number is stored in the list

		int priorCodeWord = codesList.get(0);
		int codeWord;

		printToOutput(out, indexedTable[priorCodeWord]);
		

		for (int i = 1; i < codesList.size(); i++) {
			codeWord = codesList.get(i);

			if (indexedTable[codeWord] != null) {
				// Present in dictionary
				insertIntoArray(nextLoc, indexedTable[priorCodeWord]
						+ indexedTable[codeWord].charAt(0));

				printToOutput(out, indexedTable[codeWord]);

			} else if (indexedTable[codeWord] == null) {
				// Not in dictionary
				insertIntoArray(nextLoc, indexedTable[priorCodeWord]
						+ indexedTable[priorCodeWord].charAt(0));

				printToOutput(out, indexedTable[priorCodeWord]
						+ indexedTable[priorCodeWord].charAt(0));
			}
			priorCodeWord = codeWord;
		}

		out.flush();
		out.close();
	}

	/**
	 * Write the bytes to the output file
	 * 
	 * @param out
	 * @param str
	 */
	public static void printToOutput(DataOutputStream out, String str) {

		try {
			//out.write(str.getBytes());
			out.writeBytes(str);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Array insertion is not done in this as it is normally done. Once the
	 * array is full all the previous patters are disregarded.
	 * 
	 * @param key
	 * @param value
	 */
	public void insertIntoArray(int location, String value) {

		if (location >= indexedTable.length) {
			// Array is full
			nextLoc = 0;
			indexedTable = new String[(int) Math.pow(2, 12)];
			for (int i = 0; i < 256; i++) {
				indexedTable[nextLoc] = ((char) (i)) + "";
				nextLoc++;
			}

		}
		indexedTable[nextLoc] = value;
		nextLoc++;

	}

	public static void main(String[] args) throws Exception {

	}
}
