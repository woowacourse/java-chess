package chess.domain.grid;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Lines {
    private static final String ROW_REFERENCE = "87654321";


    private List<Line> lines;

    public Lines(List<Line> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public List<Line> lines() {
        return lines;
    }

    public Piece piece(final Position position) {
        char x = position.x();
        char y = position.y();
        Line line = line(y);
        return line.piece(x);
    }

    public boolean isEmpty(Position position){
        return piece(position).isEmpty();
    }

    private Line line(final char y) {
        int index = ROW_REFERENCE.indexOf(y);
        return lines.get(index);
    }

    public void assign(final Position position, final Piece piece) {
        char x = position.x();
        char y = position.y();
        Line line = line(y);
        line.assignPiece(x, piece);
        piece(position).moveTo(position);
    }
}
