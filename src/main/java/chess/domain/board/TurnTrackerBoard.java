package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class TurnTrackerBoard {
    private final Board board;
    private Color color;

    public TurnTrackerBoard(final Board board, final Color color) {
        this.board = board;
        this.color = color;
    }

    public TurnTrackerBoard move(Position from, Position to) {
        return new TurnTrackerBoard(board.move(from, to, color), color.opposite());
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
