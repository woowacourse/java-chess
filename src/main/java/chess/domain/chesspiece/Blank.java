package chess.domain.chesspiece;

import java.util.List;

import chess.domain.Position;
import chess.domain.Team;

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
	public List<Position> makePath(ChessPiece chessPiece) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void validateMove(ChessPiece chessPiece) {
		throw new UnsupportedOperationException();
	}

}