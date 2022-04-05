package chess.domain.piece;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public enum Type {

    KING("king", 0.0, KingPiece::new),
    QUEEN("queen", 9.0, QueenPiece::new),
    BISHOP("bishop", 3.0, BishopPiece::new),
    KNIGHT("knight", 2.5, KnightPiece::new),
    ROOK("rook", 5.0, RookPiece::new),
    PAWN("pawn", 1.0, PawnPiece::new),
    EMPTY("empty", 0.0, color -> new EmptyPiece()),
    ;

    private final String name;
    private final double score;
    private final Function<Color, Piece> create;

    Type(final String name, final double score, final Function<Color, Piece> create) {
        this.name = name;
        this.score = score;
        this.create = create;
    }

    public static Type from(final String name) {
        return Arrays.stream(Type.values())
                .filter(type -> Objects.equals(type.name, name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Type입니다."));
    }

    public Piece createPiece(final Color color) {
        return create.apply(color);
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
