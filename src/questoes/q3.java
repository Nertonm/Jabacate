package questoes;

public class q3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 42339, i;
		String res = Integer.toString(num);
		for (i = 0; num > 0; i++) 
			num /= 10;
		while (i >= 0){
			i--;
			System.out.print(res[i]);
		}
	}

}
