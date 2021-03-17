package chess.manager;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;

public class ChessManager {
    private final Board board;

    public ChessManager() {
        this.board = BoardInitializer.initiateBoard();
    }

    public Board getBoard() {
        return board;
    }
}
