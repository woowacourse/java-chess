package chess.domain.grid;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public final class Lines {
    private static final int ROW_INDEX_COUNT = 7;

    private final List<Line> lines;

    public Lines(final List<Line> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public final List<Line> lines() {
        return lines;
    }

    public final void assign(final Position position, final Piece piece) {
        Column column = position.column();
        Row row = position.row();
        Line line = line(row);
        line.assignPiece(column, piece);
        piece(position).moveTo(position);
    }

    public final Piece piece(final Position position) {
        Column column = position.column();
        Row row = position.row();
        Line line = line(row);
        return line.piece(column);
    }

    public final boolean isEmpty(final Position position) {
        return piece(position).isEmpty();
    }

    private Line line(final Row row) {
        return lines.get(invertRowNumber(row));
    }

    private int invertRowNumber(final Row row) {
        return ROW_INDEX_COUNT - row.getIndex();
    }
}
