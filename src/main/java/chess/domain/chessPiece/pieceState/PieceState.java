package chess.domain.chessPiece.pieceState;

public interface PieceState {

	default PieceState shiftNextState() {
		return new MovedState();
	}

	int getPawnMovableRange();

}
