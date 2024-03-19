package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.PieceType;
import chess.domain.Position;
import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    private final Set<Direction> directions;

    public Rook(Color color) {
        super(color, PieceType.ROOK);
        this.directions = Direction.ofStraight();
    }

    public Set<Position> calculateMovablePositions(Position currentRookPosition, Board board) {
        Set<Position> movablePositions = new HashSet<>();
        directions.forEach(direction -> {
            Position position = currentRookPosition;
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
