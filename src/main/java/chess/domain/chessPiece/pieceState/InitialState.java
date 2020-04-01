package chess.domain.chessPiece.pieceState;

public class InitialState extends ChessMoveState {

	private static final int INITIAL_STATE_PAWN_MOVABLE_RANGE = 2;

	@Override
	public int getPawnMovableRange() {
		return INITIAL_STATE_PAWN_MOVABLE_RANGE;
	}

}
