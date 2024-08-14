/**
 * 
 */
package questoes;

/**
 * 
 */
public class q2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int bin = 10;
		int dec = 0, res;
		for (int n = 0; bin > 0; n++) {
			res = bin % 10;
			dec += res * Math.pow(2, n);
			bin /= 10;
		}
		System.out.print(dec);
	}

}
