import controller.ChessFrontController;

import java.sql.SQLException;

public class Application {

    public static void main(final String[] args) throws SQLException {
        final ChessFrontController chessFrontController = new ChessFrontController();
        chessFrontController.run();
    }
}
