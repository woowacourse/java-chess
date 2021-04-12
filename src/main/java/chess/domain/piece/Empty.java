package chess.domain.piece;

import chess.domain.grid.Column;
import chess.domain.grid.Row;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;

public final class Empty extends Piece {
    private static final char NAME = '.';

    public Empty(final char x, final int y) {
        super(Color.BLACK, x, y);
    }

    public Empty(final char x, final char y) {
        super(Color.BLACK, x, y);
    }

    public Empty(final Position position) {
        super(Color.BLACK, position);
    }

    public Empty(final Column column, final Row row) {
        super(Color.BLACK, column, row);
    }

    public Empty(final Color color, final Position position) {
        super(Color.BLACK, position);
    }

    @Override
    public final Color color() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public final List<Direction> directions() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public final int stepRange() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public final char name() {
        return NAME;
    }

    @Override
    public final double score() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }
}
