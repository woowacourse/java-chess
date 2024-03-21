package chess.view;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public enum PieceSign {

    EMPTY(EmptyPiece.class,"."),
    PAWN(Pawn.class, "p"),
    ROOK(Rook.class, "r"),
    KNIGHT(Knight.class, "n"),
    BISHOP(Bishop.class, "b"),
    QUEEN(Queen.class, "q"),
    KING(King.class, "k")
    ;

    private final Class<? extends Piece> pieceClass;
    private final String sign;

    PieceSign(Class<? extends Piece> pieceClass, String sign) {
        this.pieceClass = pieceClass;
        this.sign = sign;
    }

    public static String findSign(Piece piece) {
        for (PieceSign value : values()) {
            if (piece.getClass() == value.pieceClass) {
                if(piece.isSameColor(Color.BLACK)){
                    return value.sign.toUpperCase();
                }
                return value.sign;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 piece입니다.");
    }
}
