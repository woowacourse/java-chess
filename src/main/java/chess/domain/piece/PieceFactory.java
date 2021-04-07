package chess.domain.piece;

import java.util.Arrays;
import java.util.function.Function;

import chess.domain.chess.Color;

public enum PieceFactory {
    BISHOP("BISHOP", Bishop::from),
    BLANK("BLANK", (Color color) -> Blank.INSTANCE),
    KING("KING", King::from),
    KNIGHT("KNIGHT", Knight::from),
    PAWN("PAWN", Pawn::from),
    QUEEN("QUEEN", Queen::from),
    ROOK("ROOK", Rook::from);

    private final String name;
    private final Function<Color, Piece> pieceFunction;

    PieceFactory(String name,
                 Function<Color, Piece> pieceFunction) {
        this.name = name;
        this.pieceFunction = pieceFunction;
    }

    public static Piece create(String name, Color color) {
        final PieceFactory pieceFactoryByName = findPieceFactoryByName(name);
        return pieceFactoryByName.pieceFunction.apply(color);
    }

    private static PieceFactory findPieceFactoryByName(String name) {
        return Arrays.stream(PieceFactory.values())
                     .filter(pieceFactory -> pieceFactory.name.equals(name))
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("잘못된 피스의 이름입니다."));
    }
}
