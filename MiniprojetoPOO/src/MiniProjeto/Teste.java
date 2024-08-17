package MiniProjeto;
import java.util.Scanner;

public class Teste {

	public static void main(String[] args) {
	    Scanner scanf = new Scanner(System.in);
        int numClubes = -2;
        do {
        System.out.print("========================================\n");
        if (numClubes % 2 != 0) {
        	System.out.print("|           Selecione Novamente        |\n");
        }
        System.out.print("|           Quantos Clubes?            |\n");
        System.out.print("========================================\n");
        numClubes = scanf.nextInt();
        }	while (numClubes < 2 || numClubes % 2 != 0);
        numClubes++; // To match array size
    	Clube[] clubes = new Clube[numClubes];
        for(int i = 0; i < numClubes; i++) {
	        System.out.print("========================================\n");
	        System.out.print("|           Nome do Clube?             |\n");
	        System.out.print("========================================\n");
			clubes[i] = new Clube();
			clubes[i].nome = scanf.nextLine();
        }
	}
	
}
