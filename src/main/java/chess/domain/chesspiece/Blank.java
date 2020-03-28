package chess.domain.chesspiece;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;

public class Blank extends ChessPiece {
	private static final String NAME = ".";

	public Blank(Position position) {
		super(position, Team.BLANK);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean isNeedCheckPath() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Positions makePath(ChessPiece targetPiece) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void validateCanGo(ChessPiece targetPiece) {
		throw new UnsupportedOperationException();
	}

}