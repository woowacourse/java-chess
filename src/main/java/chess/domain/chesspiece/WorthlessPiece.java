package chess.domain.chesspiece;

import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.Team;

public abstract class WorthlessPiece extends ChessPiece {

	public WorthlessPiece(Position position, Team team) {
		super(position, team);
	}

	@Override
	public Positions makePath(ChessPiece chessPiece) {
		throw new UnsupportedOperationException();
	}

}
