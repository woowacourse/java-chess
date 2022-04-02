package chess.view;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Symbol {

    KING(piece -> piece.isSameType(King.class), "k"),
    QUEEN(piece -> piece.isSameType(Queen.class), "q"),
    ROOK(piece -> piece.isSameType(Rook.class), "r"),
    BISHOP(piece -> piece.isSameType(Bishop.class), "b"),
    KNIGHT(piece -> piece.isSameType(Knight.class), "n"),
    PAWN(piece -> piece.isSameType(Pawn.class),"p");

    private static final String NOT_EXIST_PIECE = "존재하지 않는 기물입니다.";

    private final Predicate<Piece> piece;
    private final String symbol;

    Symbol(Predicate<Piece> piece, String symbol) {
        this.piece = piece;
        this.symbol = symbol;
    }

    public static Symbol findSymbol(Piece targetPiece) {
        return Arrays.stream(values())
                .filter(symbol -> symbol.piece.test(targetPiece))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PIECE));
    }

    public String symbol(Piece piece) {
        if (piece.isColor(Color.BLACK)) {
            return this.symbol.toUpperCase();
        }
        return this.symbol;
    }
}
