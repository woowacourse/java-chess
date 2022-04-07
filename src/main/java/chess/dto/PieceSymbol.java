package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

import java.util.Arrays;

public enum PieceSymbol {

    BLACK_PAWN("♟"),
    BLACK_ROOK("♜"),
    BLACK_KNIGHT("♞"),
    BLACK_BISHOP("♝"),
    BLACK_QUEEN("♛"),
    BLACK_KING("♚"),

    WHITE_PAWN("♙"),
    WHITE_ROOK("♖"),
    WHITE_KNIGHT("♘"),
    WHITE_BISHOP("♗"),
    WHITE_QUEEN("♕"),
    WHITE_KING("♔"),
    ;

    public static final String NONE_PIECE_SYMBOL = ".";

    private final String symbol;

    PieceSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public static Piece getPiece(final String symbol) {
        Pieces pieces = new Pieces();
        return pieces.getPiece(getPieceSymbol(symbol));
    }

    public static String getSymbol(final Piece other) {
        if (other == null) {
            return NONE_PIECE_SYMBOL;
        }
        PieceSymbol pieceSymbol = other.getPieceSymbol();
        return pieceSymbol.symbol;
    }

    private static PieceSymbol getPieceSymbol(final String symbol) {
        return Arrays.stream(PieceSymbol.values())
                .filter(pieceSymbol -> pieceSymbol.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피스 심볼 정보입니다."));
    }
}
