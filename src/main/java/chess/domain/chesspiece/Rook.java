package chess.domain.chesspiece;

import static chess.domain.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;

public class Rook extends ValuablePiece {
	private static final List<Direction> DIRECTIONS;
	private static final String NAME = "r";

	static {
		DIRECTIONS = Arrays.asList(UP, DOWN, LEFT, RIGHT);
	}

	public Rook(Position position, Team team) {
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
