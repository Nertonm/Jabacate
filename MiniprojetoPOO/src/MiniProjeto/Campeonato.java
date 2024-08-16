package MiniProjeto;
import java.util.Random;

public class Campeonato {
	public Clube[] clubes;
	public void jogarCampeonato(Clube[] clubes) {
		/*deverá fazer um arranjo da coleção de clubes 2 a 2, de forma que cada clube 
		  jogue com todos os outros clubes do campeonato em um jogo de ida e um jogo de volta. */
		
	}
	private void jogarPartida(Clube m, Clube v) {
		/* irá sortear um placar como sendo dois inteiros entre 0 e 5 representando a quantidade de gols que cada time marcou. 
		Após esse sorteio, o método verifica o resultado, chamando os respectivos métodos para ganhar, empatar e perder dos dois clubes */
		
        Random random = new Random();
		int golsM = random.nextInt(6);
		int golsV = random.nextInt(6);
		if (golsM == golsV) {
			m.empatar();
			v.empatar();
		}
		if (golsM >= golsV) {
			m.ganhar(golsM - golsV);
			v.perder(golsV - golsM);
		} else {
			m.perder(golsM - golsV);
			v.ganhar(golsV - golsM);
		}

	}
	public void getClassificacao() {}
	public void getCampeao() {}
	
}
