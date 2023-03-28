package domain.config;

import domain.piece.Bishop;
import domain.piece.EmptyPiece;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.type.Color;
import domain.type.PieceType;
import java.util.Arrays;

public enum PieceSetting {

    ROOK_WHITE(PieceType.ROOK, Color.WHITE, Rook.makeWhite()),
    ROOK_BLACK(PieceType.ROOK, Color.BLACK, Rook.makeBlack()),
    KNIGHT_WHITE(PieceType.KNIGHT, Color.WHITE, Knight.makeWhite()),
    KNIGHT_BLACK(PieceType.KNIGHT, Color.BLACK, Knight.makeBlack()),
    BISHOP_WHITE(PieceType.BISHOP, Color.WHITE, Bishop.makeWhite()),
    BISHOP_BLACK(PieceType.BISHOP, Color.BLACK, Bishop.makeBlack()),
    QUEEN_WHITE(PieceType.QUEEN, Color.WHITE, Queen.makeWhite()),
    QUEEN_BLACK(PieceType.QUEEN, Color.BLACK, Queen.makeBlack()),
    KING_WHITE(PieceType.KING, Color.WHITE, King.makeWhite()),
    KING_BLACK(PieceType.KING, Color.BLACK, King.makeBlack()),
    PAWN_WHITE(PieceType.PAWN, Color.WHITE, Pawn.makeWhite()),
    PAWN_BLACK(PieceType.PAWN, Color.BLACK, Pawn.makeBlack()),
    EMPTY(PieceType.EMPTY, Color.NONE, EmptyPiece.make()),
    ;

    private final PieceType pieceType;
    private final Color color;
    private final Piece piece;

    PieceSetting(final PieceType pieceType, final Color color, final Piece piece) {
        this.pieceType = pieceType;
        this.color = color;
        this.piece = piece;
    }

    public static Piece findByPieceTypeAndColor(final PieceType pieceType, final Color color) {
        return Arrays.stream(PieceSetting.values())
            .filter(pieceSetting -> pieceSetting.pieceType.equals(pieceType) && pieceSetting.color.equals(color))
            .map(pieceSetting -> pieceSetting.piece)
            .findFirst()
            .orElse(EMPTY.piece);
    }
}
