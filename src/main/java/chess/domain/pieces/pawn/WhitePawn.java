package chess.domain.pieces.pawn;

import chess.domain.pieces.piece.Color;

public class WhitePawn extends Pawn {

    private static final int INITIAL_WHITE_PAWN_RANK = 1;
    private static final int DEFAULT_WHITE_PAWN_MOVE = 1;
    private static final int INITIAL_WHITE_PAWN_MOVE = 2;

    public WhitePawn() {
        super(Color.WHITE, INITIAL_WHITE_PAWN_RANK, DEFAULT_WHITE_PAWN_MOVE, INITIAL_WHITE_PAWN_MOVE);
    }
}
