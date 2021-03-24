package chess.domain.grid;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public final class Lines {
    private static final int DIFFERENCE_SIZE_AND_INDEX = 1;

    private final List<Line> lines;

    public Lines(final List<Line> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public List<Line> lines() {
        return lines;
    }

    public void assign(final Position position, final Piece piece) {
        char x = position.x();
        char y = position.y();
        Line line = line(Row.row(y));
        line.assignPiece(x, piece);
        piece(position).moveTo(position);
    }

    public Piece piece(final Position position) {
        char x = position.x();
        char y = position.y();
        Line line = line(Row.row(y));
        return line.piece(x);
    }

    public boolean isEmpty(final Position position) {
        return piece(position).isEmpty();
    }

    private Line line(final Row row) {
        int index = row.index();
        int linesLastIndex = lines.size() - DIFFERENCE_SIZE_AND_INDEX;
        return lines.get(linesLastIndex - index);
    }
}
