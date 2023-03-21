package chess;

import chess.controller.ChessGameController;
import chess.repository.ChessJdbcDao;
import chess.repository.ConnectionGenerator;
import chess.service.ChessGame;

public class ChessGameApplication {
    public static void main(String[] args) {
        final ConnectionGenerator connectionGenerator = new ConnectionGenerator();
        final ChessJdbcDao chessDao = new ChessJdbcDao(connectionGenerator);
        final ChessGame chessGame = new ChessGame(chessDao);
        final ChessGameController chessGameController = new ChessGameController(chessGame);
        chessGameController.run();
    }
}
