package chess.view.output;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum PieceSymbol {
    KING(PieceType.KING, "k"),
    QUEEN(PieceType.QUEEN, "q"),
    KNIGHT(PieceType.KNIGHT, "n"),
    BISHOP(PieceType.BISHOP, "b"),
    ROOK(PieceType.ROOK, "r"),
    PAWN(PieceType.PAWN, "p"),
    EMPTY(PieceType.EMPTY, "."),
    ;

    private final PieceType pieceType;
    private final String display;

    PieceSymbol(final PieceType pieceType, final String display) {
        this.pieceType = pieceType;
        this.display = display;
    }

    public static String getDisplay(final Piece piece) {
        PieceSymbol findSymbol = Arrays.stream(PieceSymbol.values())
                .filter(pieceSymbol -> pieceSymbol.pieceType == piece.getPieceType())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 종류의 말입니다."));
        return convertByColor(findSymbol, piece.getColor());
    }

    private static String convertByColor(final PieceSymbol pieceSymbol, final Color color) {
        if (color == Color.BLACK) {
            return pieceSymbol.display.toUpperCase();
        }

        return pieceSymbol.display;
    }
}
