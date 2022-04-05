package chess.service;

import chess.domain.Board;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.Ready;

public class ChessService {
    private ChessGame chessGame;

    public ChessService() {
        chessGame = new Ready();
    }

    public Board getBoard() {
        return chessGame.getBoard();
    }
}
