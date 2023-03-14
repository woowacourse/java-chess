package chess.model.board;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.piece.Type;

public class PieceFixture {
    public static final Piece WHITE_BISHOP = new Piece(Color.WHITE, Type.BISHOP);
    public static final Piece BLACK_KNIGHT = new Piece(Color.BLACK, Type.KNIGHT);
    public static final Piece WHITE_PAWN = new Piece(Color.WHITE, Type.PAWN);


    public static final Position A1 = new Position(File.A, Rank.FIRST);
    public static final Position B2 = new Position(File.B, Rank.SECOND);
}
