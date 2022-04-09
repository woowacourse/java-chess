package chess.domain.piece;

import chess.domain.game.Color;

import java.util.Arrays;
import java.util.function.Function;

public enum Type {
    Bishop("B", Bishop::new),
    King("K", King::new),
    Knight("N", Knight::new),
    Pawn("P", Pawn::new),
    Queen("Q", Queen::new),
    Rook("R", Rook::new);

    private final String name;
    private final Function<Color, ChessPiece> create;

    Type(String name, Function<Color, ChessPiece> create) {
        this.name = name;
        this.create = create;
    }

    public static Type from(final String name) {
        return Arrays.stream(Type.values())
                .filter(type -> type.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물 종류입니다."));
    }

    public ChessPiece createPiece(Color color) {
        return create.apply(color);
    }

    public String getName() {
        return name;
    }

}
