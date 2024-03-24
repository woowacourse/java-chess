package chess.view.matcher;

import chess.domain.piece.PieceType;

import java.util.Arrays;

public enum PieceNameMatcher {
    WHITE_PAWN("p", PieceType.WHITE_PAWN),
    BLACK_PAWN("P", PieceType.BLACK_PAWN),
    WHITE_ROOK("r", PieceType.WHITE_ROOK),
    BLACK_ROOK("R", PieceType.BLACK_ROOK),
    WHITE_KNIGHT("n", PieceType.WHITE_KNIGHT),
    BLACK_KNIGHT("N", PieceType.BLACK_KNIGHT),
    WHITE_BISHOP("b", PieceType.WHITE_BISHOP),
    BLACK_BISHOP("B", PieceType.BLACK_BISHOP),
    WHITE_KING("k", PieceType.WHITE_KING),
    BLACK_KING("K", PieceType.BLACK_KING),
    WHITE_QUEEN("q", PieceType.WHITE_QUEEN),
    BLACK_QUEEN("Q", PieceType.BLACK_QUEEN),
    ;

    private final String name;
    private final PieceType type;

    PieceNameMatcher(String name, PieceType type) {
        this.name = name;
        this.type = type;
    }

    public static String findName(PieceType type) {
        return Arrays.stream(values())
                .filter(typeName -> typeName.type == type)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 PieceType 입니다."))
                .name;
    }
}
