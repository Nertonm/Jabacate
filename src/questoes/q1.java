package questoes;
import java.util.Scanner;

public class q1 {

	public static void main(String[] args) {
		int a,b,c;
		a = 1;
		b = 20;
		c = 3;
		if (a + b < c || a + c < b || b + c < a) {
			System.out.println("Não é");
		} else {
			System.out.println("É");
		}
	}

}
