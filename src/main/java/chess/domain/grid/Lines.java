package chess.domain.grid;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public final class Lines {
    private static final int DIFFERENCE_SIZE_AND_INDEX = 1;
    private static final int LINE_COUNT = 8;
    private static final int PIECE_COUNT_IN_ONE_LINE = 8;

    private final List<Line> lines;

    public Lines(final List<Line> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public static Lines from(final List<Piece> pieces) {
        List<Line> lines = new ArrayList<>();

        for (int i = 0; i < LINE_COUNT; i++) {
            List<Piece> piecesInOneLine = new ArrayList<>();
            for (int j = 0; j < PIECE_COUNT_IN_ONE_LINE; j++) {
                piecesInOneLine.add(pieces.get(LINE_COUNT * i + j));
            }
            Line line = Line.from(piecesInOneLine);
            lines.add(line);
        }
        return new Lines(lines);
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

    public List<Piece> pieces() {
        List<Piece> pieces = new ArrayList<>();
        for (Line line : lines) {
            pieces.addAll(line.pieces());
        }
        return pieces;
    }
}
