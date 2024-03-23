package chess.view;

import chess.domain.piece.Bishop;
import chess.domain.piece.BlackPawn;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import java.util.Arrays;

public enum PieceSign {

    EMPTY(EmptyPiece.class, "."),
    BLACK_PAWN(BlackPawn.class, "P"),
    WHITE_PAWN(WhitePawn.class, "p"),
    ROOK(Rook.class, "r"),
    KNIGHT(Knight.class, "n"),
    BISHOP(Bishop.class, "b"),
    QUEEN(Queen.class, "q"),
    KING(King.class, "k");

    private final Class<? extends Piece> pieceClass;
    private final String sign;

    PieceSign(Class<? extends Piece> pieceClass, String sign) {
        this.pieceClass = pieceClass;
        this.sign = sign;
    }

    public static String findSign(Piece piece) {
        if (piece.isSameColor(Color.BLACK)) {
            return findPieceSign(piece).toUpperCase();
        }
        return findPieceSign(piece);
    }

    private static String findPieceSign(Piece piece) {
        return Arrays.stream(values())
                .filter(value -> value.pieceClass == piece.getClass())
                .map(value -> value.sign)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 piece입니다."));
    }
}
