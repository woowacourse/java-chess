import controller.ChessController;
import service.ChessService;

public class Application {
    public static void main(String[] args) {
        new ChessController(new ChessService()).run();
    }
}
