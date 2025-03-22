package chess.piece;

import chess.board.ChessBoard;
import chess.board.Color;
import chess.board.Movement;
import chess.board.Position;
import java.util.Set;

public class Queen implements Piece {

    private static final Set<Movement> DIRECTIONS = Set.of(
            Movement.UP,
            Movement.DOWN,
            Movement.RIGHT,
            Movement.LEFT,
            Movement.LEFT_DOWN,
            Movement.LEFT_UP,
            Movement.RIGHT_DOWN,
            Movement.RIGHT_UP
    );

    private final Color color;

    public Queen(Color color) {
        this.color = color;
    }

    @Override
    public boolean canMoveToDestination(ChessBoard chessBoard, Position start, Position end) {
        for (Movement direction : DIRECTIONS) {
            Position current = new Position(start.row(), start.column());
            while (!current.equals(end) && current.canMove(direction)) {
                if (!chessBoard.isEmpty(current)) {
                    break;
                }
                current = current.move(direction);
            }
            if (current.equals(end)) {
                return chessBoard.isNotSameColor(current, color);
            }
        }
        return false;
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color == color;
    }
}
