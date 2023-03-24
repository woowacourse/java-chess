import controller.ChessController;

public final class Application {
    public static void main(String[] args) {
        final ChessController chessController = new ChessController();
        chessController.run();
    }
}
