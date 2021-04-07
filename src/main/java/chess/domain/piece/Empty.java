package chess.domain.piece;

import chess.domain.grid.Column;
import chess.domain.grid.Row;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;

public final class Empty extends Piece {
    public static final char NAME = '.';

    public Empty(final Column column, final Row row) {
        super(Color.BLACK, column.getName(), row.getName());
    }

    public Empty(final Color color, final char x, final char y) {
        super(color, x, y);
    }

    public Empty(final char x, final int y) {
        super(Color.BLACK, x, y);
    }

    public Empty(final char x, final char y) {
        super(Color.BLACK, x, y);
    }

    public Empty(final Position position) {
        super(Color.BLACK, position);
    }

    @Override
    public Piece create(Color color, char x, char y) {
        return new Empty(x, y);
    }

    @Override
    public Color color() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public List<Direction> directions() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public int stepRange() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public char name() {
        return NAME;
    }

    @Override
    public double score() {
        throw new UnsupportedOperationException("해당 메서드를 사용하면 안 됩니다.");
    }

    @Override
    public boolean match(char pieceName) {
        return pieceName == NAME;
    }
}
