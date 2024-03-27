import controller.ChessController;

import java.sql.SQLException;

public class Application {

    public static void main(final String[] args) throws SQLException {
        final ChessController chessController = new ChessController();
        chessController.run();
    }
}
