package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.game.ChessGame;

public class ChessService {

    public void initializeBoard() {
        ChessGame chessGame = new ChessGame(new Board(BoardInitializer.initialize()));
    }
}
