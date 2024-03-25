package chess.view.matcher;

import chess.domain.piece.PieceType;

import java.util.Arrays;

public enum PieceNameMatcher {
    WHITE_PAWN(PieceType.WHITE_PAWN, "p"),
    BLACK_PAWN(PieceType.BLACK_PAWN, "P"),
    WHITE_ROOK(PieceType.WHITE_ROOK, "r"),
    BLACK_ROOK(PieceType.BLACK_ROOK, "R"),
    WHITE_KNIGHT(PieceType.WHITE_KNIGHT, "n"),
    BLACK_KNIGHT(PieceType.BLACK_KNIGHT, "N"),
    WHITE_BISHOP(PieceType.WHITE_BISHOP, "b"),
    BLACK_BISHOP(PieceType.BLACK_BISHOP, "B"),
    WHITE_KING(PieceType.WHITE_KING, "k"),
    BLACK_KING(PieceType.BLACK_KING, "K"),
    WHITE_QUEEN(PieceType.WHITE_QUEEN, "q"),
    BLACK_QUEEN(PieceType.BLACK_QUEEN, "Q"),
    ;

    private final PieceType type;
    private final String name;

    PieceNameMatcher(PieceType type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String matchByType(PieceType type) {
        return Arrays.stream(values())
                .filter(typeName -> typeName.type == type)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 PieceType 입니다."))
                .name;
    }

    public static boolean isPresentType(final PieceType type) {
        return Arrays.stream(values())
                .anyMatch(pieceNameMatcher -> pieceNameMatcher.type == type);
    }
}
