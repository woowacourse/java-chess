package chess;

import chess.chessboard.Side;
import chess.piece.King;
import chess.piece.Piece;

public class PieceFixture {
    public static final Piece BLACK_KING = King.getKingOf(Side.BLACK);
    public static final Piece WHITE_KING = King.getKingOf(Side.WHITE);
    public static final Piece BLACK_QUEEN = King.getKingOf(Side.BLACK);
    public static final Piece WHITE_QUEEN = King.getKingOf(Side.WHITE);
}
