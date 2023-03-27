package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.PieceType;

import java.util.Arrays;

public enum PieceSymbol {
    BISHOP(PieceType.BISHOP, "B", "b"),
    ROOK(PieceType.ROOK, "R", "r"),
    QUEEN(PieceType.QUEEN, "Q", "q"),
    KNIGHT(PieceType.KNIGHT, "N", "n"),
    KING(PieceType.KING, "K", "k"),
    BLACK_PAWN(PieceType.BLACK_PAWN, "P", "P"),
    WHITE_PAWN(PieceType.WHITE_PAWN, "p", "p"),
    BLANK(PieceType.BLANK, " ", " ");

    private final PieceType pieceType;
    private final String bigSymbol;
    private final String smallSymbol;

    PieceSymbol(PieceType pieceType, String bigSymbol, String smallSymbol) {
        this.pieceType = pieceType;
        this.bigSymbol = bigSymbol;
        this.smallSymbol = smallSymbol;
    }

    public static PieceSymbol getByPieceType(PieceType pieceType) {
        return Arrays.stream(PieceSymbol.values())
                .filter(symbol -> symbol.pieceType == pieceType)
                .findAny().orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물 종류입니다."));
    }

    public String getPrintingSymbol(Color color) {
        if (color == Color.BLACK) {
            return bigSymbol;
        }
        return smallSymbol;
    }
}
