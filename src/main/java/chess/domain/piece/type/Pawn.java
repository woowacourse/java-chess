package chess.domain.piece.type;

import chess.domain.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

import java.util.List;

public final class Pawn extends Piece {
    private static final List<Direction> BLACK_DIRECTION = List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
    private static final List<Direction> WHITE_DIRECTION = List.of(Direction.TOP, Direction.TOP_LEFT, Direction.TOP_RIGHT);

    public Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean isInMovableRange(Position source, Position target) {
        Direction direction = source.findDirectionTo(target);
        return isMovableDirection(direction) && isMovableDistance(source, target, direction);
    }

    private boolean isMovableDirection(Direction direction) {
        if (color.isWhite()) {
            return WHITE_DIRECTION.contains(direction);
        }
        return BLACK_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(Position source, Position target, Direction direction) {
        int distance = source.calculateDistanceTo(target);

        if (color.isWhite() && source.isRank(ChessRank.TWO) && direction == Direction.TOP) {
            return (distance == 1 || distance == 2);
        }
        if (color.isBlack() && source.isRank(ChessRank.SEVEN) && direction == Direction.DOWN) {
            return (distance == 1 || distance == 2);
        }
        return distance == 1;
    }
}
