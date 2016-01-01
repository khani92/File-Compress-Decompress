public class LZWCompression {

	/**
	 * S. No. Filename Original Size Compressed Size Degree of
	 * compression(Compressed size/ Original size)*100 Data Structure used Time
	 * taken (millis) 
	 * 
	 * 1. Shortwords.txt 50 bytes 56 bytes 112% TreeMap 24 HashMap 15
	 * 
	 * 2. Words.html 2.37 MB 1.01 MB 42.61% TreeMap 4315 HashMap 2347
	 * 
	 * 3. CrimeLatLonXY1990.csv 277 KB 133 KB 48.01% TreeMap 1015 HashMap 688
	 * 
	 * 4. 01_Overview.mp4 23.8 MB 32.2 MB 135.29 TreeMap 52389 HashMap 26158
	 * 
	 * 
	 * README This program works correctly for all file formats and can compress
	 * any file size as long as the system on which that program is running has
	 * enough memory. The program can compress/decompress audio/video files and
	 * even files in other languages like Chinese. The program speed analysis is
	 * detailed in the table above. The program runs faster when HashMaps are
	 * used than when TreeMaps are used. This is expected behavior because tree
	 * maps spends extra effort in sorting all the entries in it at each
	 * insertion while hash maps insert data in random positions.
	 * 
	 * ALL THIS INFORMATION IS PRESENT IN THE README DOCUMENT UNDER THE SRC FOLDER
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		try {
			if (args != null && args.length > 0) {
				if (args[0].equalsIgnoreCase("C")) {
					// Compress
					if (args[1] != null & args[2] != null) {
						Compressor comp = new Compressor(args[1], args[2]);
						comp.startCompressing();
					} else {
						System.out
								.println("Enter the name of the files with their valid file extensions as a command line argument");
					}
				} else if (args[0].equalsIgnoreCase("D")) {
					// De-compress
					if (args[1] != null & args[2] != null) {
						DeCompressor deComp = new DeCompressor(args[1], args[2]);
						deComp.startDecompressing();
					} else {
						System.out
								.println("Enter the name of the files with their valid file extensions as a command line argument");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
