package chess.domain.piece;

import chess.domain.Camp;

public class PieceTexture {

    public static final Piece WHITE_ROOK = new Rook(Camp.WHITE);
    public static final Piece WHITE_KNIGHT = new Knight(Camp.WHITE);
    public static final Piece WHITE_BISHOP = new Bishop(Camp.WHITE);
    public static final Piece WHITE_QUEEN = new Queen(Camp.WHITE);
    public static final Piece WHITE_KING = new King(Camp.WHITE);
    public static final Piece WHITE_PAWN = new Pawn(Camp.WHITE);

    public static final Piece BLACK_ROOK = new Rook(Camp.BLACK);
    public static final Piece BLACK_KNIGHT = new Knight(Camp.BLACK);
    public static final Piece BLACK_BISHOP = new Bishop(Camp.BLACK);
    public static final Piece BLACK_QUEEN = new Queen(Camp.BLACK);
    public static final Piece BLACK_KING = new King(Camp.BLACK);
    public static final Piece BLACK_PAWN = new Pawn(Camp.BLACK);
}
