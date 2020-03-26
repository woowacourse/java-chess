package chess.domain.chesspiece;

import static chess.domain.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.Direction;
import chess.domain.NameUtils;
import chess.domain.Position;
import chess.domain.Team;

public class Bishop extends ValuablePiece {
	private static final List<Direction> DIRECTIONS;
	private static final String NAME = "d";

	static {
		DIRECTIONS = Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_DOWN, RIGHT_UP);
	}

	public Bishop(Position position, Team team) {
		super(position, team);
	}

	@Override
	public String getName() {
		return NameUtils.parseName(NAME, team);
	}

	@Override
	public List<Position> makePath(ChessPiece chessPiece) {
		return moveManager.makePath(chessPiece.position, DIRECTIONS);
	}

	@Override
	public boolean isNeedCheckPath() {
		return true;
	}

}
