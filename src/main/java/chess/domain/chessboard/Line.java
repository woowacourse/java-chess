package chess.domain.chessboard;

import chess.domain.chesspiece.Piece;
import chess.domain.position.File;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Line {
    private final List<Piece> line;

    public Line(List<Piece> line) {
        this.line = line;
    }

    public Line update(File file, Piece chessPiece) {
        List<Piece> line = new ArrayList<>(this.line);
        line.set(file.getValue(), chessPiece);
        return new Line(line);
    }

    public List<Piece> getLine() {
        return Collections.unmodifiableList(line);
    }

    public Piece getChessPiece(File file) {
        return line.get(file.getValue());
    }
}
