package chess.domain.chesspiece;

import java.util.Arrays;
import java.util.List;

import chess.domain.Direction;
import chess.domain.utils.NameUtils;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.Team;

public class Queen extends ValuablePiece {
	private static final List<Direction> DIRECTIONS;
	private static final String NAME = "q";

	static {
		DIRECTIONS = Arrays.asList(Direction.values());
	}

	public Queen(Position position, Team team) {
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
	public Positions makePath(ChessPiece chessPiece) {
		return moveManager.makePath(chessPiece.position, DIRECTIONS);
	}
}
