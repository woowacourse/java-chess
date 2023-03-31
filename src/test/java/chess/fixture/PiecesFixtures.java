package chess.fixture;

import static chess.domain.piece.Side.BLACK;
import static chess.domain.piece.Side.WHITE;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

public class PiecesFixtures {

    public static final Piece PAWN_WHITE_B3 = new Pawn(PositionFixtures.B3, WHITE);
}
