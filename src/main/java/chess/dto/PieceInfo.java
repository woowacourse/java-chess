package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;

import java.util.Arrays;

public enum PieceInfo {

    BLACK_PAWN("♟", 1),
    BLACK_ROOK("♜", 5),
    BLACK_KNIGHT("♞", 2.5),
    BLACK_BISHOP("♝", 3),
    BLACK_QUEEN("♛", 9),
    BLACK_KING("♚", 0),

    WHITE_PAWN("♙", 1),
    WHITE_ROOK("♖", 5),
    WHITE_KNIGHT("♘", 2.5),
    WHITE_BISHOP("♗", 3),
    WHITE_QUEEN("♕", 9),
    WHITE_KING("♔", 0),
    ;

    public static final String NONE_PIECE_SYMBOL = ".";

    private final String symbol;
    private final double score;

    PieceInfo(final String symbol, final double score) {
        this.symbol = symbol;
        this.score = score;
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

    public static double getScore(final Piece other) {
        PieceInfo pieceInfo = other.getPieceInfo();
        return pieceInfo.score;
    }

    private static PieceInfo getPieceInfo(final String symbol) {
        return Arrays.stream(PieceInfo.values())
                .filter(pieceInfo -> pieceInfo.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피스 심볼 정보입니다."));
    }
}
