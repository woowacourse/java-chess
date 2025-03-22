package chess.piece;

import chess.board.ChessBoard;
import chess.board.Color;
import chess.board.Movement;
import chess.board.Position;
import java.util.Objects;
import java.util.Set;

public class Bishop implements Piece {

    private static final Set<Movement> DIRECTIONS = Set.of(
            Movement.LEFT_DOWN,
            Movement.LEFT_UP,
            Movement.RIGHT_DOWN,
            Movement.RIGHT_UP
    );

    private final PieceType pieceType = PieceType.BISHOP;
    private final Color color;

    public Bishop(Color color) {
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
        if (!(o instanceof Bishop bishop)) {
            return false;
        }
        return pieceType == bishop.pieceType && color == bishop.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
