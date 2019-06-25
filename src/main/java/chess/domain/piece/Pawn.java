package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Row;

public class Pawn extends Piece {
    private static final Row STARTING_BLACK_ROW = Row.from(6);
    private static final Row STARTING_WHITE_ROW = Row.from(2);

    private static final int FIRST_WHITE_DISTANCE = 2;
    private static final int FIRST_BLACK_DISTANCE = -2;

    private static final int ZERO_VECTOR = 0;

    private int distance;

    Pawn(final Color color, final Position position, final int distance) {
        super(Type.PAWN, color, position);
        this.distance = distance;
    }

    public static Pawn createWhite(final Position position) {
        return new Pawn(Color.WHITE, position, FIRST_WHITE_DISTANCE);
    }

    public static Pawn createBlack(final Position position) {
        return new Pawn(Color.BLACK, position, FIRST_BLACK_DISTANCE);
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        final int vector = origin.vectorOfRow(target);
        if (origin.isPerpendicular(target) && (isBottomMovable(vector) || isTopMovable(vector))) {
            decreaseDistanceIfFirst();
            return true;
        }
        return false;
    }

    private boolean isTopMovable(final int vector) {
        return this.distance <= vector && vector < ZERO_VECTOR;
    }

    private boolean isBottomMovable(final int vector) {
        return this.distance >= vector && vector > ZERO_VECTOR;
    }

    private void decreaseDistanceIfFirst() {
        if (isWhite() && getPosition().isSameRow(STARTING_WHITE_ROW)
                || (isBlack() && getPosition().isSameRow(STARTING_BLACK_ROW))) {
            distance /= 2;
        }
    }

    @Override
    public boolean isValidAttack(final Position origin, final Position target) {
        if (!origin.isDiagonal(target)) {
            return false;
        }
        final int vector = origin.vectorOfRow(target);
        return origin.sumRowAndColumn(target) == 2 && isSameSign(vector);
    }

    private boolean isSameSign(final int vector) {
        return Integer.compare(0, this.distance) == Integer.compare(0, vector);
    }
}
