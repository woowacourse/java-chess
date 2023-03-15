package chess;

import chess.piece.*;

public enum Shape {

    WHITE_PAWN(new Pawn(Side.WHITE), "p"),
    BLACK_PAWN(new Pawn(Side.BLACK), "P"),
    WHITE_KNIGHT(new Knight(Side.WHITE), "n"),
    BLACK_KNIGHT(new Knight(Side.BLACK), "N"),
    WHITE_BISHOP(new Bishop(Side.WHITE), "b"),
    BLACK_BISHOP(new Bishop(Side.BLACK), "B"),
    WHITE_ROOK(new Rook(Side.WHITE), "r"),
    BLACK_ROOK(new Rook(Side.BLACK), "R"),
    WHITE_QUEEN(new Queen(Side.WHITE), "q"),
    BLACK_QUEEN(new Queen(Side.BLACK), "Q"),
    WHITE_KING(new King(Side.WHITE), "k"),
    BLACK_KING(new King(Side.BLACK), "K"),
    BLANK(new Empty(Side.BLANK), ".");

    private final ChessPiece chessPiece;
    private final String name;

    Shape(ChessPiece chessPiece, String name) {
        this.chessPiece = chessPiece;
        this.name = name;
    }

    public static String getNameByClass(ChessPiece chessPiece) {
        for (Shape shape : Shape.values()) {
            if (shape.chessPiece.equals(chessPiece)) {
                return shape.name;
            }
        }
        return BLANK.name;
    }
}
