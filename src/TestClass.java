/*public class TestClass {

	public static void main(String[] args) {
		
		byte b = -56;
		System.out.println("In binary  :" + Integer.toBinaryString(b & 0x00FF));
		System.out.println((char) 200);
		
		Integer apple  = 253; 
		//System.out.println(Integer.toHexString(apple));
		Integer banana = 0xDF;
		char ch ='b';
		for(int i =65;i<95;i++){
			System.out.println((char) i + " = " + (byte) i );	
		}
		
		int i = 0x0ABC;
		int j = 0x0FF0;
		int left2 = i & j;
		left2 = (left2 >>> 4);
		//left2 = left2 & 0x00FF ;
		System.out.println(left2 == 0xAB);
		System.out.println(left2);
		System.out.println(0xAB);

		String hexString = Integer.toHexString(i);
		System.out.println(hexString);
		byte[] array = hexString.getBytes();

		String s1;
		byte x = (byte) 'a';
		s1 = String.format("%8s", Integer.toBinaryString(x)).replace(' ', '0');
		System.out.println(s1);
		System.out.println(Integer.toBinaryString(x & 0xFF));

		byte left4bits = (byte) (0xF0 & x);

		s1 = String.format("%8s", Integer.toBinaryString(left4bits & 0xFF))
				.replace(' ', '0');
		System.out.println(s1);

		byte right4bits = (byte) (0x0F & x);
		s1 = String.format("%8s", Integer.toBinaryString(right4bits & 0xFF))
				.replace(' ', '0');
		System.out.println(s1);
		// System.out.println(Integer.toBinaryString(12323));

		
		 * for(int i =0; i< (Math.pow(2, 16)-1) ;i++){ System.out.print((char)
		 * i); System.out.print(" " + i); System.out.println(); }
		 

		
		 * for(char ch = 0; ch <= 255; ch++) { System.out.print("" + ch); }
		 
	}
}
*/