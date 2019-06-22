package chess.domain;

import chess.domain.RuleImpl.*;

public enum PieceSymbol {
    WHITE_PAWN(Piece.Color.WHITE, Pawn.NAME, "♙"),
    WHITE_BISHOP(Piece.Color.WHITE, Bishop.NAME, "♗"),
    WHITE_KING(Piece.Color.WHITE, King.NAME, "♔"),
    WHITE_KNIGHT(Piece.Color.WHITE, Knight.NAME, "♘"),
    WHITE_QUEEN(Piece.Color.WHITE, Queen.NAME, "♕"),
    WHITE_ROOK(Piece.Color.WHITE, Rook.NAME, "♖"),
    BLACK_PAWN(Piece.Color.BLACK, Pawn.NAME, "♟️"),
    BLACK_BISHOP(Piece.Color.BLACK, Bishop.NAME, "♝"),
    BLACK_KING(Piece.Color.BLACK, King.NAME, "♚"),
    BLACK_KNIGHT(Piece.Color.BLACK, Knight.NAME, "♞"),
    BLACK_QUEEN(Piece.Color.BLACK, Queen.NAME, "♛"),
    BLACK_ROOK(Piece.Color.BLACK, Rook.NAME, "♜");


    public static final String EMPTY_SYMBOL = " ";

    private final Piece.Color color;
    private final String name;
    private final String symbol;

    PieceSymbol(final Piece.Color color, final String name, final String symbol) {
        this.color = color;
        this.name = name;
        this.symbol = symbol;
    }

    public static String getSymbol(Piece.Color color, Rule rule) {
        for (final PieceSymbol pieceSymbol : values()) {
            if(pieceSymbol.color == color && pieceSymbol.name.equals(rule.getName())){
                return pieceSymbol.symbol;
            }
        }
        return EMPTY_SYMBOL;
    }
}
