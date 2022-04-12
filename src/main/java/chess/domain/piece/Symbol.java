package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

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

    public static String findBySymbol(Class<? extends Piece> otherPiece) {
        return Arrays.stream(values())
            .filter(symbol -> symbol.piece.equals(otherPiece))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 유형의 기물은 존재하지 않습니다."))
            .value;
    }

    public static String makePieceName(Position position, Map<Position, Piece> board) {
        Piece piece = board.get(position);
        if (piece.isSameColor(BLACK)) {
            return BLACK.name() + Symbol.findBySymbol(piece.getClass());
        }
        return WHITE.name() + Symbol.findBySymbol(piece.getClass());
    }
}
