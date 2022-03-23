package chess.domain;

import java.util.Iterator;
import java.util.List;

public class ChessMen implements Iterable<ChessPiece> {
    private final List<ChessPiece> chessPieces;

    public ChessMen(List<ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    @Override
    public Iterator<ChessPiece> iterator() {
        return chessPieces.iterator();
    }
}
