package view.mapper;

import domain.Piece;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceMapper {

    ROOK(Piece::isRook, "R"),
    KNIGHT(Piece::isKnight, "N"),
    BISHOP(Piece::isBishop, "B"),
    QUEEN(Piece::isQueen, "Q"),
    KING(Piece::isKing, "K"),
    PAWN(Piece::isPawn, "P"),
    ;

    private final Function<Piece, Boolean> function;
    private final String symbol;

    PieceMapper(Function<Piece, Boolean> function, String symbol) {
        this.function = function;
        this.symbol = symbol;
    }

    public static String toSymbol(Piece piece) {
        String symbol = Arrays.stream(values())
                .filter(it -> it.function.apply(piece))
                .findFirst()
                .map(it -> it.symbol)
                .orElseThrow();

        if (piece.isBlack()) {
            return symbol;
        }
        return symbol.toLowerCase();
    }
}
