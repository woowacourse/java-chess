package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class ChessGame {
    private final Board board;

    public ChessGame() {
        this.board = new Board();
    }

    public void initialize() {
        board.initialize();
    }

    public void move(final String source, final String target) {
        board.move(source, target);
    }

    public boolean isInitialized() {
        return board.isInitialized();
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
