package chess.domain.chesspiece;

import java.util.Arrays;
import java.util.List;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.utils.NameUtils;

public class Queen extends ValuablePiece {
	private static final List<Direction> DIRECTIONS;
	private static final String NAME = "q";

	static {
		DIRECTIONS = Arrays.asList(Direction.values());
	}

	public Queen(Position position, Team team) {
		super(position, team, NAME);
	}

	@Override
	public boolean isNotNeedCheckPath() {
		return false;
	}

	@Override
	public Positions makePathAndValidate(ChessPiece targetPiece) {
		return moveManager.makePath(targetPiece.position, DIRECTIONS);
	}
}
