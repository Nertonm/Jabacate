package MiniProjeto;
import java.util.Random;
import java.util.Arrays;
import java.util.Comparator;

public class Campeonato {
	public Clube[] clubes;
	public void jogarCampeonato() {
		/*deverá fazer um arranjo da coleção de clubes 2 a 2, de forma que cada clube 
		  jogue com todos os outros clubes do campeonato em um jogo de ida e um jogo de volta. */
		int qtdClubes = clubes.length;
		for (int i = 0; i < qtdClubes; i++) {
			for(int j = i+1; j < qtdClubes; j++) {
				jogarPartida(clubes[i], clubes[j]);
				jogarPartida(clubes[j], clubes[i]);
			}
		}
		getClassificacao();
		getCampeao();
	}
	
	private void jogarPartida(Clube m, Clube v) {
		/* irá sortear um placar como sendo dois inteiros entre 0 e 5 representando a quantidade de gols que cada time marcou. 
		Após esse sorteio, o método verifica o resultado, chamando os respectivos métodos para ganhar, empatar e perder dos dois clubes */
        String novaLinha = System.lineSeparator();
        Random random = new Random();
		int golsM = random.nextInt(6);
		int golsV = random.nextInt(6);
		if (golsM == golsV) {
			m.empatar();
			v.empatar();
	        System.out.print(m.nome + " empatou com " + v.nome);
	        System.out.print(novaLinha);
	        System.out.print("placar: " + golsM + " a " + golsM);
	        System.out.print(novaLinha);
	        System.out.print(novaLinha);
		}
		else if (golsM > golsV) {
			m.ganhar(golsM - golsV);
			v.perder(golsV - golsM);
	        System.out.print(m.nome + " ganhou de " + v.nome);
	        System.out.print(novaLinha);
	        System.out.print("placar: " + golsM +  " a " + golsV);
	        System.out.print(novaLinha);
	        System.out.print(novaLinha);
		} else {
			m.perder(golsM - golsV);
			v.ganhar(golsV - golsM);
	        System.out.print(v.nome + " ganhou de " + m.nome);
	        System.out.print(novaLinha);
	        System.out.print("placar:  " + golsV +  " a " + golsM);
	        System.out.print(novaLinha);
	        System.out.print(novaLinha);
		}
	}
	
	public void getClassificacao() {
		Arrays.sort(clubes, new Comparator<Clube>(){
			public int compare(Clube m, Clube v){
				if (m.pontos == v.pontos) {
					return v.saldoGols - m.saldoGols;
				}
				return v.pontos - m.pontos;
			}
		});
	}
	
	public void getCampeao() {
        String novaLinha = System.lineSeparator();
		int qtdClubes = clubes.length;
		for (int i = 0; i < qtdClubes; i++) {
	        System.out.print(i + 1 + " Lugar: ");
	        System.out.print(clubes[i].nome + " com " + clubes[i].pontos + " pontos e " + clubes[i].saldoGols + " de saldo gols");
	        System.out.print(novaLinha);
		}
	}
}
