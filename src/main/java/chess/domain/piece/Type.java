package chess.domain.piece;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public enum Type {

    KING("king", KingPiece::new),
    QUEEN("queen", QueenPiece::new),
    BISHOP("bishop", BishopPiece::new),
    KNIGHT("knight", KnightPiece::new),
    ROOK("rook", RookPiece::new),
    PAWN("pawn", PawnPiece::new),
    EMPTY("empty", color -> new EmptyPiece()),
    ;

    private final String name;
    private final Function<Color, Piece> create;

    Type(final String name, final Function<Color, Piece> create) {
        this.name = name;
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
}
