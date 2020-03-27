package chess.domain.chesspiece;

import chess.domain.Position;
import chess.domain.Team;

public abstract class ValuablePiece extends ChessPiece {

	public ValuablePiece(Position position, Team team) {
		super(position, team);
	}

	@Override
	public void validateMove(ChessPiece chessPiece) {
		throw new UnsupportedOperationException();
	}

}
