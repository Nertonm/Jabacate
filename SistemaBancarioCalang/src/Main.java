import UIconsole.UIconsole;
import service.ContaFacade;
import service.ContaService;

public class Main {
    public static void main(String[] args) {
        ContaService contaService = new ContaService();
        ContaFacade contaFacade = new ContaFacade(contaService);
        UIconsole uiConsole = new UIconsole(contaFacade);
        uiConsole.start();
    }
}