package chess.domain.piece;

import java.util.Map;

import chess.domain.Team;
import chess.domain.position.Position;

public class Blank extends Piece{

	public Blank(Position position) {
		super(new BlankStrategy(), Team.NONE, position);
	}

	@Override
	public Piece move(Position from, Position to, Map<Position, Team> teamBoard) {
		throw new UnsupportedOperationException();
	}

	@Override
	public double getScore() {
		return 0;
	}

	@Override
	public String getSymbol() {
		return ".";
	}

	@Override
	public boolean isNotNone() {
		return false;
	}
}
