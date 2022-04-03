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

    BLACK_PAWN(new Pawn(Color.BLACK), "♟"),
    BLACK_ROOK(new Rook(Color.BLACK), "♜"),
    BLACK_KNIGHT(new Knight(Color.BLACK), "♞"),
    BLACK_BISHOP(new Bishop(Color.BLACK), "♝"),
    BLACK_QUEEN(new Queen(Color.BLACK), "♛"),
    BLACK_KING(new King(Color.BLACK), "♚"),

    WHITE_PAWN(new Pawn(Color.WHITE), "♙"),
    WHITE_ROOK(new Rook(Color.WHITE), "♖"),
    WHITE_KNIGHT(new Knight(Color.WHITE), "♘"),
    WHITE_BISHOP(new Bishop(Color.WHITE), "♗"),
    WHITE_QUEEN(new Queen(Color.WHITE), "♕"),
    WHITE_KING(new King(Color.WHITE), "♔"),
    ;

    public static final String NONE_PIECE_SYMBOL = ".";

    private final Piece piece;
    private final String symbol;

    PieceSymbol(final Piece piece, final String symbol) {
        this.piece = piece;
        this.symbol = symbol;
    }

    public static String getSymbol(final Piece other) {
        return Arrays.stream(PieceSymbol.values())
                .filter(pieceSymbol -> pieceSymbol.piece.equals(other))
                .map(pieceSymbol -> pieceSymbol.symbol)
                .findFirst()
                .orElse(NONE_PIECE_SYMBOL);
    }
}
