package chess;

import chess.controller.WebUIChessController;
import java.sql.SQLException;

public class WebUIChessApplication {

    public static void main(String[] args) throws SQLException {
        final WebUIChessController controller = new WebUIChessController();
        controller.runChess();
    }

}
