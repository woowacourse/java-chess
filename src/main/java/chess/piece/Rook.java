package chess.piece;

import chess.board.ChessBoard;
import chess.board.Color;
import chess.board.Movement;
import chess.board.Position;
import java.util.Objects;
import java.util.Set;

public class Rook implements Piece {

    private static final Set<Movement> DIRECTIONS = Set.of(
            Movement.UP,
            Movement.DOWN,
            Movement.RIGHT,
            Movement.LEFT
    );

    private final PieceType pieceType = PieceType.ROOK;
    private final Color color;

    public Rook(Color color) {
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

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rook rook)) {
            return false;
        }
        return pieceType == rook.pieceType && color == rook.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
