package chess.domain.piece.type;

import chess.domain.Direction;
import chess.domain.board.Movement;
import chess.domain.board.SquareStatus;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessRank;

import java.util.List;

public final class Pawn extends Piece {
    private static final List<Direction> BLACK_DIRECTION = List.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
    private static final List<Direction> WHITE_DIRECTION = List.of(Direction.UP, Direction.UP_LEFT, Direction.UP_RIGHT);

    public Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean isMovable(final Movement movement, final SquareStatus targetStatus) {
        return isMovableDirection(movement, targetStatus) && isMovableDistance(movement);
    }

    private boolean isMovableDirection(final Movement movement, final SquareStatus targetStatus) {
        if (targetStatus.isEnemy()) {
            return canAttack(movement);
        }
        return canMove(movement);
    }

    private boolean canMove(final Movement movement) {
        Direction direction = movement.findDirection();
        if (color.isWhite()) {
            return movement.isCross() && WHITE_DIRECTION.contains(direction);
        }
        return movement.isCross() && BLACK_DIRECTION.contains(direction);
    }

    private boolean canAttack(final Movement movement) {
        Direction direction = movement.findDirection();
        if (color.isWhite()) {
            return movement.isDiagonal() && WHITE_DIRECTION.contains(direction);
        }
        return movement.isDiagonal() && BLACK_DIRECTION.contains(direction);
    }

    private boolean isMovableDistance(final Movement movement) {
        int distance = movement.calculateDistance();
        Direction direction = movement.findDirection();
        if (color.isWhite() && movement.isSourceRank(ChessRank.TWO) && direction == Direction.UP) {
            return (distance == 1 || distance == 2);
        }
        if (color.isBlack() && movement.isSourceRank(ChessRank.SEVEN) && direction == Direction.DOWN) {
            return (distance == 1 || distance == 2);
        }
        return distance == 1;
    }
}
