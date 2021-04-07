package chess.domain.piece;

import chess.domain.grid.Column;
import chess.domain.grid.Row;
import chess.domain.position.Direction;

import java.util.List;

public final class Rook extends Piece {
    public static final char NAME_WHEN_BLACK = 'R';
    public static final char NAME_WHEN_WHITE = 'r';
    private static final int STEP_RANGE = 8;
    private static final int SCORE = 5;

    public Rook(final Color color, final Column column, final Row row) {
        super(color, column.getName(), row.getName());
    }

    public Rook(final Color color, final char x, final int y) {
        super(color, x, y);
    }

    public Rook(final Color color, final char x, final char y) {
        super(color, x, y);
    }

    @Override
    public Piece create(Color color, char x, char y) {
        return new Rook(color, x, y);
    }

    @Override
    public List<Direction> directions() {
        return Direction.linearDirection();
    }

    @Override
    public int stepRange() {
        return STEP_RANGE;
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public char name() {
        if (isBlack()) {
            return NAME_WHEN_BLACK;
        }
        return NAME_WHEN_WHITE;
    }

    @Override
    public boolean match(char pieceName) {
        return pieceName == NAME_WHEN_BLACK || pieceName == NAME_WHEN_WHITE;
    }
}