package chess.domain.chesspiece;

import java.util.List;

import chess.domain.Position;
import chess.domain.Team;

public abstract class WorthlessPiece extends ChessPiece {

	public WorthlessPiece(Position position, Team team) {
		super(position, team);
	}

	@Override
	public List<Position> makePath(ChessPiece chessPiece) {
		throw new UnsupportedOperationException();
	}


}
