package chess.view;

import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum PieceTypeView {
    PAWN(PieceType.PAWN, "P"),
    BISHOP(PieceType.BISHOP, "B"),
    KING(PieceType.KING, "K"),
    KNIGHT(PieceType.KNIGHT, "N"),
    QUEEN(PieceType.QUEEN, "Q"),
    ROOK(PieceType.ROOK, "R"),
    HORSE(PieceType.HORSE, "H"),
    NONE(PieceType.NONE, "."),
    ;

    private final PieceType pieceType;
    private final String pieceName;

    PieceTypeView(PieceType pieceType, String pieceName) {
        this.pieceType = pieceType;
        this.pieceName = pieceName;
    }

    public static PieceTypeView from(PieceType pieceType) {
        return Arrays.stream(PieceTypeView.values())
                .filter(pieceTypeView -> pieceTypeView.pieceType == pieceType)
                .findAny()
                .orElse(NONE);
    }
    public  String getPieceNameBy(PieceType pieceType) {
        return Arrays.stream(PieceTypeView.values())
                .filter(pieceTypeView -> pieceTypeView.pieceType == pieceType)
                .findAny()
                .orElse(NONE)
                .pieceName;
    }
}
