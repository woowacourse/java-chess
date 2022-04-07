package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

import java.util.Arrays;

public enum PieceInfo {

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

    PieceInfo(final String symbol) {
        this.symbol = symbol;
    }

    public static Piece getPiece(final String symbol) {
        Pieces pieces = new Pieces();
        return pieces.getPiece(getPieceInfo(symbol));
    }

    public static String getSymbol(final Piece other) {
        if (other == null) {
            return NONE_PIECE_SYMBOL;
        }
        PieceInfo pieceInfo = other.getPieceInfo();
        return pieceInfo.symbol;
    }

    private static PieceInfo getPieceInfo(final String symbol) {
        return Arrays.stream(PieceInfo.values())
                .filter(pieceInfo -> pieceInfo.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피스 심볼 정보입니다."));
    }
}
