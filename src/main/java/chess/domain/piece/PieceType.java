package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceType {

    PAWN("pawn", 1.0, Pawn::new),
    KNIGHT("knight", 2.5, Knight::new),
    BISHOP("bishop", 3.0, Bishop::new),
    ROOK("rook", 5.0, Rook::new),
    QUEEN("queen", 9.0, Queen::new),
    KING("king", 0.0, King::new);

    private final String name;
    private final double score;
    private final BiFunction<Color, Position, Piece> constructor;

    PieceType(String name, double score, BiFunction<Color, Position, Piece> constructor) {
        this.name = name;
        this.score = score;
        this.constructor = constructor;
    }

    public static PieceType of(String name) {
        return Arrays.stream(values())
            .filter(pieceType -> pieceType.name.equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 체스 말 입니다."));
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public BiFunction<Color, Position, Piece> getConstructor() {
        return constructor;
    }
}
