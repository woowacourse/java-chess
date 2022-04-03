package chess.dto;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.Arrays;

public enum PieceSymbol {

    BLACK_PAWN(new Pawn(Color.BLACK), "♟", 1),
    BLACK_ROOK(new Rook(Color.BLACK), "♜", 5),
    BLACK_KNIGHT(new Knight(Color.BLACK), "♞", 2.5),
    BLACK_BISHOP(new Bishop(Color.BLACK), "♝", 3),
    BLACK_QUEEN(new Queen(Color.BLACK), "♛", 9),
    BLACK_KING(new King(Color.BLACK), "♚", 0),

    WHITE_PAWN(new Pawn(Color.WHITE), "♙", 1),
    WHITE_ROOK(new Rook(Color.WHITE), "♖", 5),
    WHITE_KNIGHT(new Knight(Color.WHITE), "♘", 2.5),
    WHITE_BISHOP(new Bishop(Color.WHITE), "♗", 3),
    WHITE_QUEEN(new Queen(Color.WHITE), "♕", 9),
    WHITE_KING(new King(Color.WHITE), "♔", 0),
    ;

    private static final String NONE_PIECE_SYMBOL = ".";
    private static final double NONE_PIECE_SCORE = 0.0;

    private final Piece piece;
    private final String symbol;
    private final double score;

    PieceSymbol(final Piece piece, final String symbol, final double score) {
        this.piece = piece;
        this.symbol = symbol;
        this.score = score;
    }

    public static String getSymbol(final Piece other) {
        return Arrays.stream(PieceSymbol.values())
                .filter(pieceSymbol -> pieceSymbol.piece.equals(other))
                .map(pieceSymbol -> pieceSymbol.symbol)
                .findFirst()
                .orElse(NONE_PIECE_SYMBOL);
    }

    public static double getScore(final Piece other) {
        return Arrays.stream(PieceSymbol.values())
                .filter(pieceSymbol -> pieceSymbol.piece.equals(other))
                .map(pieceSymbol -> pieceSymbol.score)
                .findFirst()
                .orElse(NONE_PIECE_SCORE);
    }
}
