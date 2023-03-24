package chess;

import chess.controller.ChessController;
import chess.dao.ChessGameDao;
import chess.dao.MySQLChessGameDao;
import chess.dao.template.JdbcContext;
import chess.game.ChessGame;

public class Application {
    public static void main(String[] args) {
        ChessGameDao chessGameDao = new MySQLChessGameDao(new JdbcContext());
        ChessGame chessGame = new ChessGame();
        ChessController chessController = new ChessController(chessGame, chessGameDao);
        chessController.run();
    }
}
