package MiniProjeto;

/*
  A classe Clube tem como atributos seu nome, seu número de pontos e seu saldo de gols. Os métodos ganhar, 
  empatar e perder devem incrementar os pontos e o saldo de gols de acordo com as regras do campeonato.

 */
public class Clube {
	/*** @param args*/
	public String nome;
	public int pontos;
	public int saldoGols;
	
	public void ganhar(int saldoGols) {
		pontos += 3;
	}
	public void empatar() {
		pontos++;
	}
	public void perder(int saldoGols) {}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
