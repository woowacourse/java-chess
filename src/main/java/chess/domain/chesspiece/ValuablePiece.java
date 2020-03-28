package chess.domain.chesspiece;

import chess.domain.Team;
import chess.domain.position.Position;

public abstract class ValuablePiece extends ChessPiece {

	public ValuablePiece(Position position, Team team) {
		super(position, team);
	}

	@Override
	public void validateMove(ChessPiece chessPiece) {
		throw new UnsupportedOperationException();
	}
}
