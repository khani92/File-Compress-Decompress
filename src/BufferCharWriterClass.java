import java.util.List;

public class BufferCharWriterClass {

	int bufferCapacity = 3;
	int currSize = -1;
	byte[] buffer = new byte[bufferCapacity];

	/**
	 * Write the 8 bit read from the zipped file to the buffer. In case the buff
	 * is full move all the data from the buffer to the array list using the flush method
	 * 
	 * @param b
	 * @param codesList
	 */
	public void writeData(byte b, List<Integer> codesList) {

		if (currSize == 2) {
			flushToFile(codesList);
		}
		buffer[++currSize] = b;
	}

	// This is the tricky part
	public void flushToFile(List<Integer> codesList) {
		int b1;
		int b2;
		int temp;
		for (int i = 0; i < currSize; i++) {

			if (i % 2 == 1) {
				b1 = buffer[i];
				b2 = buffer[i + 1];

				b1 = b1 << 8;
				b1 = b1 & 0x0F00;
				b2 = b2 & 0x00FF;

				temp = b1 | b2;
				temp = temp & 0x0FFF;

				//	System.out.println(temp);
				
				codesList.add(temp);
			}

			else if (i % 2 == 0) {

				b1 = buffer[i];
				b2 = buffer[i + 1];

				b1 = b1 << 4;
				b1 = b1 & 0x0FF0;
				b2 = b2 >> 4;
				b2 = b2 & 0x000F;

				temp = b1 | b2;
				temp = temp & 0xFFF;
				
				//System.out.println(temp);
				
				codesList.add(temp);
			}

		}
		currSize = -1;
		buffer = new byte[bufferCapacity];
		
	}
}
