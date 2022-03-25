package chess.domain;

import java.util.Iterator;
import java.util.List;

public class ChessMen implements Iterable<ChessPiece> {
    private final List<ChessPiece> chessPieces;

    public ChessMen(List<ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public boolean existChessPieceInPosition(ChessBoardPosition position) {
        return chessPieces.stream()
                .anyMatch(it -> it.isSamePosition(position));
    }

    @Override
    public Iterator<ChessPiece> iterator() {
        return chessPieces.iterator();
    }
}
