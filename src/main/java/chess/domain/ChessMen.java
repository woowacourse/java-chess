package chess.domain;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import java.util.Iterator;
import java.util.List;

public class ChessMen implements Iterable<ChessPiece> {
    private static final String NO_CHESS_PIECE_EXCEPTION = "[ERROR] 움직이려는 위치에 체스피스가 없습니다.";
    private final List<ChessPiece> chessPieces;

    public ChessMen(List<ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public ChessPiece getChessPieceAt(ChessBoardPosition position) {
        return chessPieces.stream()
                .filter(it -> it.isSamePosition(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_CHESS_PIECE_EXCEPTION));
    }

    public boolean existChessPieceAt(ChessBoardPosition position) {
        return chessPieces.stream()
                .anyMatch(it -> it.isSamePosition(position));
    }

    public void removeChessPieceAt(ChessBoardPosition position) {
        chessPieces.removeIf(chessPiece -> chessPiece.isSamePosition(position));
    }

    public boolean isKingDead() {
        return chessPieces.stream()
                .noneMatch(King.class::isInstance);
    }

    public double calculateScore() {
        return chessPieces.stream()
                .mapToDouble(ChessPiece::getScore)
                .sum();
    }

    @Override
    public Iterator<ChessPiece> iterator() {
        return chessPieces.iterator();
    }
}
