package chess.view.mapper;

import chess.domain.piece.Piece;

import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum PieceMapper {

    ROOK(PieceType.ROOK, "R"),
    KNIGHT(PieceType.KNIGHT, "N"),
    BISHOP(PieceType.BISHOP, "B"),
    QUEEN(PieceType.QUEEN, "Q"),
    KING(PieceType.KING, "K"),
    PAWN(PieceType.PAWN, "P"),
    EMPTY(PieceType.EMPTY, "."),
    ;

    private final PieceType pieceType;
    private final String symbol;

    PieceMapper(PieceType pieceType, String symbol) {
        this.pieceType = pieceType;
        this.symbol = symbol;
    }

    public static String toSymbol(Piece piece) {
        String symbol = Arrays.stream(values())
                .filter(it -> it.pieceType == piece.pieceType())
                .findFirst()
                .map(it -> it.symbol)
                .orElseThrow();

        if (piece.isBlack()) {
            return symbol;
        }
        return symbol.toLowerCase();
    }
}
