package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.PieceType;
import chess.domain.Position;
import java.util.HashSet;
import java.util.Set;

public class King extends Piece {
    private final Set<Direction> directions;

    public King(Color color) {
        super(color, PieceType.KING);
        this.directions = Direction.ofAll();
    }

    public Set<Position> calculateMovablePositions(Position currentPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();

        directions.forEach(direction -> {
            Position position = currentPosition;
            if (!position.canMoveNext(direction)) {
                return;
            }

            position = position.next(direction);
            Piece piece = board.findPieceByPosition(position);

            if (!isSameColor(piece)) {
                movablePositions.add(position);
            }
        });
        return movablePositions;
    }
}
