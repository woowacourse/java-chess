package chess.domain.pieces.pawn;

import chess.domain.pieces.piece.Color;

public class BlackPawn extends Pawn {

    private static final int INITIAL_BLACK_PAWN_RANK = 6;
    private static final int DEFAULT_BLACK_PAWN_MOVE = -1;
    private static final int INITIAL_BLACK_PAWN_MOVE = -2;

    public BlackPawn() {
        super(Color.BLACK, INITIAL_BLACK_PAWN_RANK, DEFAULT_BLACK_PAWN_MOVE, INITIAL_BLACK_PAWN_MOVE);
    }
}
