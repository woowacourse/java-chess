package chess.domain.chesspiece;

import static chess.domain.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.Direction;
import chess.domain.NameUtils;
import chess.domain.Position;
import chess.domain.Team;

public class King extends WorthlessPiece {
	private static final List<Direction> DIRECTIONS;
	private static final String NAME = "k";

	static {
		DIRECTIONS = Arrays.asList(UP, LEFT, RIGHT, DOWN, RIGHT_DOWN,
			RIGHT_UP, LEFT_DOWN, LEFT_UP);
	}

	public King(Position position, Team team) {
		super(position, team);
	}

	@Override
	public void validateMove(ChessPiece chessPiece) {
		moveManager.validateMove(chessPiece.position, DIRECTIONS);
	}

	@Override
	public String getName() {
		return NameUtils.parseName(NAME, team);
	}

	@Override
	public boolean isNeedCheckPath() {
		return false;
	}

}
