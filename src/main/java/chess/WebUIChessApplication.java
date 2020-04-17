package chess;

import chess.controller.WebUIChessController;

import java.sql.SQLException;

public class WebUIChessApplication {
    public static void main(String[] args) throws SQLException {
        WebUIChessController chessGame = new WebUIChessController();
        chessGame.run();
    }
}
