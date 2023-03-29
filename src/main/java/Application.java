import controller.ChessController;
import database.connection.ConnectionGenerator;
import database.connection.MySQLConnectionGenerator;

public final class Application {
    public static void main(String[] args) {
        ConnectionGenerator connectionGenerator = new MySQLConnectionGenerator();
        final ChessController chessController = new ChessController(connectionGenerator);
        chessController.run();
    }
}
