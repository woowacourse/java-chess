package chess;

import chess.controller.ChessController;
import chess.dao.ChessGameDao;
import chess.dao.MySQLChessGameDao;
import chess.game.ChessGame;

public class Application {
    public static void main(String[] args) {
        ChessGameDao chessGameDao = new MySQLChessGameDao();
        ChessGame chessGame = new ChessGame();
        ChessController chessController = new ChessController(chessGame, chessGameDao);
        chessController.run();
    }
}
