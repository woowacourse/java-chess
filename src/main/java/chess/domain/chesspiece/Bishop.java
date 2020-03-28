package chess.domain.chesspiece;

import static chess.domain.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.utils.NameUtils;

public class Bishop extends ValuablePiece {
	private static final List<Direction> DIRECTIONS;
	private static final String NAME = "b";

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
	public Positions makePathAndValidate(ChessPiece targetPiece) {
		return moveManager.makePathAndValidate(targetPiece.position, DIRECTIONS);
	}

	@Override
	public boolean isNeedCheckPath() {
		return true;
	}

}
