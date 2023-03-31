package chess;

import chess.controller.ChessGameController;
import chess.controller.ChessGameState;
import chess.dao.ChessGameDao;
import chess.domain.game.ChessGame;
import chess.service.ChessGameService;

public class Application {
    public static void main(String[] args) {
        var chessGameState = new ChessGameState(new ChessGameService(new ChessGame(), new ChessGameDao()));
        var chessGameController = new ChessGameController(chessGameState);
        
        chessGameController.execute();
    }
}
