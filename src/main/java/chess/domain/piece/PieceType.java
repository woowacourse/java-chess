package chess.domain.piece;

import chess.domain.Color;
import java.util.Arrays;
import java.util.function.Function;

public enum PieceType {

    KING("K", 0, King::new),
    QUEEN("Q", 9, Queen::new),
    ROOK("R", 5, Rook::new),
    BISHOP("B", 3, Bishop::new),
    KNIGHT("N", 2.5, Knight::new),
    PAWN("P", 1, Pawn::new),
    EMPTY(".", 0, color -> new EmptyPiece());

    private final String notation;
    private final double score;
    private final Function<Color, Piece> function;

    PieceType(String notation, double score, Function<Color, Piece> function) {
        this.notation = notation;
        this.score = score;
        this.function = function;
    }

    public static PieceType from(String notation) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.notation.equals(notation.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }

    public Piece newPiece(Color color) {
        return function.apply(color);
    }

    public String getNotation(Color color) {
        if (color.isBlack()) {
            return notation;
        }

        return notation.toLowerCase();
    }

    public double getScore() {
        return score;
    }
}
