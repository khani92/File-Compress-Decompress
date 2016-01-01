import java.io.DataOutputStream;
import java.io.IOException;

public class Buffer12BitWriterClass {

	int bufferCapacity = 3;
	int currSize = 0;
	byte[] buffer = new byte[bufferCapacity];
	DataOutputStream out;

	Buffer12BitWriterClass(DataOutputStream out) {
		this.out =  out;
	}

	/**
	 * This method is going to do many crucial things. The input integer to it
	 * will be the value we got from the map. It will take out the least
	 * significant 12 bits. Do some bit operations on it and split the 21 bits
	 * into 2 bytes and hold it in buffer array.
	 * 
	 * @param i
	 */
	public void writeData(int i) {

		int temp = i;
		if (currSize == 2) {
			flush();
		}

		// store data
		if (currSize % 2 == 0) {

			temp = temp & 0x0FF0; // Of the 12 bits to be written. Get most
			
			//	System.out.println(Integer.toBinaryString(temp));					// significant (MS) 8 bits
			
			temp = temp >>> 4; // Shift the bits to right
			buffer[currSize] = (byte) temp;
			currSize++;

			// For the remainder 4 bits
			temp = i;
			temp = temp & 0x000F;
			temp = temp << 4;
			temp = temp & 0x00F0;
			buffer[currSize] = (byte) temp;

		} else if (currSize % 2 == 1) {

			temp = temp & 0x0F00; // Most significant 4 bits of the 12 relevant
									// bits
			temp = temp >>> 8;
			buffer[currSize] = (byte) (buffer[currSize] | ((byte) temp));
			currSize++;

			temp = i;
			temp = temp & 0x00FF; // LS 8 bits of the 112 relevant bits
			buffer[currSize] = (byte) temp;
		}
	}

	/**
	 * Move all the values from the buffer to zipped file
	 */
	public void flush() {
		
		for(int i =0; i<= currSize; i++){
			try {
				out.writeByte(buffer[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		currSize = 0;
		buffer = new byte[bufferCapacity];
		
	}
}
