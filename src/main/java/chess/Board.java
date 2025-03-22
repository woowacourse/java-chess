package chess;

import chess.piece.Piece;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Piece> pieces = new ArrayList<>();

    public void put(Piece piece) {
        pieces.add(piece);
    }

    public boolean existSameTeam(Position position, Color color) {
        return pieces.stream().anyMatch(piece ->
                        piece.getPosition().equals(position) &&
                        piece.getColor() == color);
    }

    public boolean existPiece(Position position) {
        return pieces.stream().anyMatch(piece -> piece.getPosition().equals(position));
    }

    public Piece findPiece(Position position, Color color) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().equals(position) && piece.getColor() == color)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("움직일 수 없는 기물입니다."));
    }

    public Piece findPiece(Position position) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findFirst()
                .get();
    }

    public void removeByPosition(Position position) {
        pieces.removeIf(piece -> piece.getPosition().equals(position));
    }
}
