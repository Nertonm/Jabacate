package MiniProjeto;
import java.util.Scanner;

public class Teste {

	public static void main(String[] args) {
	    Scanner primeiraPergunta = new Scanner(System.in);
        int numClubes = 2;
        do {
        System.out.print("========================================\n");
        if (numClubes < 2) {
        	System.out.print("|           Selecione Novamente        |\n");
        }
        System.out.print("|           Quantos Clubes?            |\n");
        System.out.print("========================================\n");
        numClubes = primeiraPergunta.nextInt();
        }	while (numClubes < 2);
	    Scanner scanf = new Scanner(System.in);
    	Clube[] clubes = new Clube[numClubes];
        System.out.print("========================================\n");
        System.out.print("|          Iniciando Campeonato        |\n");
        System.out.print("========================================\n");
        for(int i = 0; i < numClubes; i++) {
	        System.out.print("========================================\n");
	        System.out.print("|           Nome do Clube?             |\n");
	        System.out.print("========================================\n");
			clubes[i] = new Clube();
			clubes[i].nome = scanf.nextLine();
        }
        Campeonato campeonato = new Campeonato();
        campeonato.clubes = clubes;
		campeonato.jogarCampeonato();        
	}

}
