package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Movement;
import java.util.List;

public class Pawn extends Piece {
    private static final List<Direction> WHITE_ATTACK_DIRECTION = List.of(Direction.UP_LEFT, Direction.UP_RIGHT);
    private static final List<Direction> BLACK_ATTACK_DIRECTION = List.of(Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
    private static final Direction WHITE_DIRECTION = Direction.UP;
    private static final Direction BLACK_DIRECTION = Direction.DOWN;
    private static final int DEFAULT_MOVE_DISTANCE = 1;
    private static final int INITIAL_MOVE_DISTANCE = 2;
    public static final int BLACK_INITIAL_SQUARE = 6;
    public static final int WHITE_INITIAL_SQUARE = 1;

    public Pawn(final Color color) {
        super(color, Type.PAWN);
    }

    @Override
    public boolean canMove(final Movement movement, final Piece destinationPiece) {
        if (hasDestinationPiece(destinationPiece)) {
            return isAttack(movement);
        }
        if (isSameColor(Color.WHITE)) {
            return isWhiteMove(movement);
        }
        return isBlackMove(movement);
    }

    private boolean hasDestinationPiece(final Piece destinationPiece) {
        return destinationPiece != null;
    }

    private boolean isAttack(final Movement movement) {
        if (isSameColor(Color.WHITE)) {
            return isWhiteAttack(movement);
        }
        return isBlackAttack(movement);
    }

    private boolean isWhiteAttack(final Movement movement) {
        System.out.println(movement.direction());
        return WHITE_ATTACK_DIRECTION.contains(movement.direction())
                && movement.calculateMaxDistance() == DEFAULT_MOVE_DISTANCE;
    }

    private boolean isBlackAttack(final Movement movement) {
        System.out.println(movement.direction());
        return BLACK_ATTACK_DIRECTION.contains(movement.direction())
                && movement.calculateMaxDistance() == DEFAULT_MOVE_DISTANCE;
    }

    private boolean isWhiteMove(final Movement movement) {
        if (isWhiteInitialSquare(movement)) {
            return isWhiteFirstMove(movement) || isWhiteDefaultMove(movement);
        }
        return isWhiteDefaultMove(movement);
    }

    private boolean isWhiteInitialSquare(final Movement movement) {
        return movement.getSourceRankIndex() == WHITE_INITIAL_SQUARE;
    }

    private boolean isWhiteFirstMove(final Movement movement) {
        return WHITE_DIRECTION.equals(movement.direction())
                && movement.calculateMaxDistance() == INITIAL_MOVE_DISTANCE;
    }

    private boolean isWhiteDefaultMove(final Movement movement) {
        return WHITE_DIRECTION.equals(movement.direction())
                && movement.calculateMaxDistance() == DEFAULT_MOVE_DISTANCE;
    }

    private boolean isBlackMove(final Movement movement) {
        if (isBlackInitialSquare(movement)) {
            return isBlackFirstMove(movement) || isBlackDefaultMove(movement);
        }
        return isBlackDefaultMove(movement);
    }

    private boolean isBlackInitialSquare(final Movement movement) {
        return movement.getSourceRankIndex() == BLACK_INITIAL_SQUARE;
    }

    private boolean isBlackFirstMove(final Movement movement) {
        return BLACK_DIRECTION.equals(movement.direction())
                && movement.calculateMaxDistance() == INITIAL_MOVE_DISTANCE;
    }

    private boolean isBlackDefaultMove(final Movement movement) {
        return BLACK_DIRECTION.equals(movement.direction())
                && movement.calculateMaxDistance() == DEFAULT_MOVE_DISTANCE;
    }
}
