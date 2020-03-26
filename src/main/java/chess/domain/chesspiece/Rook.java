package chess.domain.chesspiece;

import static chess.domain.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.Direction;
import chess.domain.NameUtils;
import chess.domain.Position;
import chess.domain.Team;

public class Rook extends ValuablePiece {
	private static final List<Direction> DIRECTIONS;
	private static final String NAME = "r";

	static {
		DIRECTIONS = Arrays.asList(UP, DOWN, LEFT, RIGHT);
	}
	public Rook(Position position, Team team) {
		super(position, team);
	}

	@Override
	public String getName() {
		return NameUtils.parseName(NAME, team);
	}

	@Override
	public boolean isNeedCheckPath() {
		return true;
	}

	@Override
	public List<Position> makePath(ChessPiece chessPiece) {
		return moveManager.makePath(chessPiece.position, DIRECTIONS);
	}

}
