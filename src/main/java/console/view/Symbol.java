package console.view;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.Arrays;

public enum Symbol {

    QUEEN(Queen.class, "Q"),
    KING(King.class, "K"),
    ROOK(Rook.class, "R"),
    BISHOP(Bishop.class, "B"),
    KNIGHT(Knight.class, "N"),
    PAWN(Pawn.class, "P");

    private final Class<? extends Piece> piece;
    private final String value;

    Symbol(Class<? extends Piece> piece, String value) {
        this.piece = piece;
        this.value = value;
    }

    public static String findBySymbol(Class<? extends Piece> piece2) {
        return Arrays.stream(values())
            .filter(piece -> piece.piece.equals(piece2))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 유형의 기물은 존재하지 않습니다."))
            .value;
    }
}
