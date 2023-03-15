package chess.model.board;

import chess.model.piece.Bishop;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.piece.PieceType;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;

public class PieceFixture {

    public static final Piece WHITE_BISHOP = new Bishop(PieceColor.WHITE);
    public static final Piece BLACK_KNIGHT = new Knight(PieceColor.BLACK);
    public static final Piece WHITE_PAWN = new Pawn(PieceColor.WHITE);

    public static final Position A1 = new Position(File.A, Rank.FIRST);
    public static final Position B2 = new Position(File.B, Rank.SECOND);
}
