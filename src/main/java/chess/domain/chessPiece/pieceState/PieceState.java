package chess.domain.chessPiece.pieceState;

public interface PieceState {

	PieceState shiftNextState();

	int getPawnMovableRange();

}
