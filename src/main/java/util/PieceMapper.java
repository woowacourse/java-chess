package util;

import domain.piece.PieceCategory;
import java.util.Arrays;

public enum PieceMapper {
    WHITE_PAWN(PieceCategory.WHITE_PAWN, "p"),
    WHITE_ROOK(PieceCategory.WHITE_ROOK, "r"),
    WHITE_KNIGHT(PieceCategory.WHITE_KNIGHT, "n"),
    WHITE_BISHOP(PieceCategory.WHITE_BISHOP, "b"),
    WHITE_QUEEN(PieceCategory.WHITE_QUEEN, "q"),
    WHITE_KING(PieceCategory.WHITE_KING, "k"),
    BLACK_PAWN(PieceCategory.BLACK_PAWN, "P"),
    BLACK_ROOK(PieceCategory.BLACK_ROOK, "R"),
    BLACK_KNIGHT(PieceCategory.BLACK_KNIGHT, "N"),
    BLACK_BISHOP(PieceCategory.BLACK_BISHOP, "B"),
    BLACK_QUEEN(PieceCategory.BLACK_QUEEN, "Q"),
    BLACK_KING(PieceCategory.BLACK_KING, "K"),
    EMPTY_PIECE(PieceCategory.EMPTY_PIECE, ".");


    private final PieceCategory category;
    private final String text;

    PieceMapper(PieceCategory category, String text) {
        this.category = category;
        this.text = text;
    }

    public static String convertPieceCategoryToText(PieceCategory category) {
        return Arrays.stream(PieceMapper.values())
                .filter(pieceCategory -> pieceCategory.category.equals(category))
                .findFirst()
                .map(pieceMapper -> pieceMapper.text)
                .orElseThrow(
                        () -> new IllegalStateException("서버 내부 에러 - 존재하지 않는 PieceCategory를 Mapping 시도했습니다."));
    }
}
