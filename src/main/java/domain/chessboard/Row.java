package domain.chessboard;

import domain.coordinate.Position;
import domain.piece.base.ChessPiece;
import java.util.List;

public class Row {

    private final List<ChessPiece> pieces;

    public Row(List<ChessPiece> pieces) {
        this.pieces = pieces;
    }

    public ChessPiece getPiece(Position column) {
        return pieces.get(column.getValue());
    }

    public void replace(Position column, ChessPiece chessPiece) {
        pieces.set(column.getValue(), chessPiece);
    }

    public List<ChessPiece> getPieces() {
        return List.copyOf(pieces);
    }
}
