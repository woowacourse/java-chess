package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.PieceType;
import chess.domain.Position;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {
    private final Set<Direction> directions;

    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
        this.directions = Direction.ofDiagonal();
    }

    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();
        directions.forEach(direction -> {
            Position position = currentPosition;
            while (position.canMoveNext(direction)) {
                position = position.next(direction);
                Piece piece = board.findPieceByPosition(position);

                if (isSameColor(piece)) {
                    break;
                }

                movablePositions.add(position);

                if (!piece.isEmpty()) {
                    break;
                }
            }
        });
        return movablePositions;
    }
}
