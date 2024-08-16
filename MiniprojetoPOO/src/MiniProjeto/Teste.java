package MiniProjeto;

public class Teste {

	public static void main(String[] args) {
		Clube a = new Clube();
		Clube b = new Clube();

		a.ganhar(3);
		a.ganhar(2);
		a.empatar();

		System.out.print(a.pontos);
		System.out.print(b.pontos);

	}

}
