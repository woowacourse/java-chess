package chess.domain.chesspiece;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;

public abstract class WorthlessPiece extends ChessPiece {

	public WorthlessPiece(Position position, Team team) {
		super(position, team);
	}

	@Override
	public Positions makePath(ChessPiece targetPiece) {
		throw new UnsupportedOperationException();
	}
}
