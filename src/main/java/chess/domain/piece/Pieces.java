package chess.domain.piece;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pieces {

    private static final int TOTAL_KING_COUNT = 2;

    private static final String NON_PIECE_EXCEPTION_MESSAGE = "해당위치에는 말이 없습니다.";

    private final List<Piece> pieces;

    public Pieces(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public Piece extractPiece(Position position) {
        return pieces.stream()
            .filter(piece -> piece.isAt(position))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(NON_PIECE_EXCEPTION_MESSAGE));
    }

    public boolean isOccupied(Position position) {
        return pieces.stream()
            .anyMatch(piece -> piece.isAt(position));
    }

    public void remove(Piece targetPiece) {
        pieces.remove(targetPiece);
    }

    public boolean hasLessThanTotalKingCount() {
        int kingCount = (int) pieces.stream()
            .filter(Piece::isKing)
            .count();

        return kingCount < TOTAL_KING_COUNT;
    }

    public boolean isAnyPieceExistInPositions(List<Position> positions) {
        long count = 0;
        for (Position position : positions) {
            count += pieces.stream()
                .filter(piece -> piece.isAt(position))
                .count();
        }
        return count != 0;
    }

    public List<Piece> extractPiecesOf(Color color) {
        return pieces.stream()
            .filter(piece -> piece.isSameColor(color))
            .collect(Collectors.toUnmodifiableList());
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return "Pieces{" +
            "pieces=" + pieces +
            '}';
    }

    public Color findKingWinner() {
        if (findKingCount() != 1) {
            return Color.NONE;
        }

        return pieces
            .stream()
            .filter(Piece::isKing)
            .map(Piece::getColor)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("승자를 찾을 수 없습니다."));
    }

    private long findKingCount() {
        return pieces
            .stream()
            .filter(Piece::isKing)
            .count();
    }
}


