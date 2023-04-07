package chess;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;

public class PieceFixture {
    public static final Piece BLACK_KING = King.getKingOf(Color.BLACK);
    public static final Piece WHITE_KING = King.getKingOf(Color.WHITE);
    public static final Piece BLACK_QUEEN = King.getKingOf(Color.BLACK);
    public static final Piece WHITE_QUEEN = King.getKingOf(Color.WHITE);
}
