package chess.domain.chessPiece.pieceState;

public abstract class ChessMoveState implements PieceState {

	@Override
	public PieceState shiftNextState() {
		return new MovedState();
	}

	@Override
	public abstract int getPawnMovableRange();

}
