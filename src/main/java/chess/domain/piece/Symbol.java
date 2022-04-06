package console.view;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.Arrays;
import java.util.Map;

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

    public static String makePieceName(Position position, Map<Position, Piece> board) {
        Piece piece = board.get(position);
        if (piece.isSameColor(Color.BLACK)) {
            return "B" + Symbol.findBySymbol(piece.getClass());
        }
        return "W" + Symbol.findBySymbol(piece.getClass());
    }
}
