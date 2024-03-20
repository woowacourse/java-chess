package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.position.Position;
import java.util.HashSet;
import java.util.Set;

public abstract class MultiStepPiece extends Piece {
    public MultiStepPiece(Color color, PieceType pieceType, Set<Direction> directions) {
        super(color, pieceType, directions);
    }

    @Override
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
