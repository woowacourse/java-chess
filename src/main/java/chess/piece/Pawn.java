package chess.piece;

import static chess.game.Direction.*;
import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.game.Direction;
import chess.game.Position;
import java.util.List;

public class Pawn extends AbstractPiece {

    private static final List<Direction> whiteDirections =  List.of(N, NW, NE);
    private static final List<Direction> blackDirections =  List.of(S, SW, SE);

    public static double SCORE = 1;
    public static double REDUCED_SCORE = 0.5;

    public Pawn(final Color color) {
        super(Name.PAWN, color);
    }

    @Override
    public boolean canMove(final Position from, final Position to) {
        if (isEqualColor(WHITE)) {
            return whiteDirections.stream()
                    .anyMatch(direction -> canMove(from, to, direction));
        }
        if (isEqualColor(BLACK)) {
            return blackDirections.stream()
                    .anyMatch(direction -> canMove(from, to, direction));
        }
        throw new IllegalArgumentException("폰이 이동할 수 있는 경로가 아닙니다.");
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    private boolean canMove(final Position from, final Position to, final Direction direction) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);
        return direction.canPawnMove(columnDistance, rowDistance, from.isPawnBeginningRow());
    }
}
