package chess.domain.chessPiece.pieceState;

public class MovedState extends ChessMoveState {

	public static final int MOVED_STATE_PAWN_MOVABLE_RANGE = 1;

	@Override
	public int getPawnMovableRange() {
		return MOVED_STATE_PAWN_MOVABLE_RANGE;
	}

}
