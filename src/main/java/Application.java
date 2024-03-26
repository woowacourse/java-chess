import controller.ChessController;

public class Application {
    public static void main(final String[] args)  {
        final ChessController chessController = new ChessController();
        chessController.start();
    }
}
