package view;

import domain.Color;
import domain.Piece;
import domain.PieceType;
import java.util.Arrays;

public enum PieceTypeFormat {
    PAWN("P", PieceType.PAWN),
    KNIGHT("N", PieceType.KNIGHT),
    ROOK("R", PieceType.ROOK),
    BISHOP("B", PieceType.BISHOP),
    QUEEN("Q", PieceType.QUEEN),
    KING("K", PieceType.KING);

    private final String whiteFormat;
    private final String blackFormat;
    private final PieceType pieceType;

    PieceTypeFormat(final String format, final PieceType pieceType) {
        this.whiteFormat = format.toLowerCase();
        this.blackFormat = format;
        this.pieceType = pieceType;
    }

    public static String findFormat(final Piece piece) {
        return Arrays.stream(values())
                .filter(type -> type.pieceType == piece.getPieceType())
                .map(type -> piece.getColor() == Color.WHITE ? type.whiteFormat : type.blackFormat)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("피스 타입이 없습니다."));


    }


}
