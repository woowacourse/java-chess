package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Division {
    private static final String KNIGHT_DISPLAYNAME = "n";
    private static final double KNIGHT_SCORE = 2.5;
    public static final int FIRST_POSITION = 2;
    public static final int SECOND_POSITION = 1;

    public Knight(final Color color, final Position position) {
        super(color, KNIGHT_DISPLAYNAME, position);
    }

    @Override
    public void moveToEmpty(final Position to, final Pieces pieces) {
        final int diffRow = Math.abs(position.diffRow(to));
        final int diffColumn = Math.abs(position.diffColumn(to));

        if ((diffRow == FIRST_POSITION && diffColumn == SECOND_POSITION) ||
            (diffRow == SECOND_POSITION && diffColumn == FIRST_POSITION)) {
            position = to;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void moveForKill(final Position to, final Pieces pieces) {
        this.moveToEmpty(to, pieces);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return KNIGHT_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
