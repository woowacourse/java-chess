package chess.domain.chesspiece;

import static chess.domain.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.utils.NameUtils;

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
	public boolean isNotNeedCheckPath() {
		return false;
	}

	@Override
	public Positions makePathAndValidate(ChessPiece targetPiece) {
		return moveManager.makePath(targetPiece.position, DIRECTIONS);
	}

}