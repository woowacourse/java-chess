package chess.domain.chesspiece;

import static chess.domain.Direction.*;

import java.util.List;

import chess.domain.Direction;
import chess.domain.MoveManager;
import chess.domain.NameUtils;
import chess.domain.Position;
import chess.domain.Team;

public class Pawn extends WorthlessPiece {
	private static final String NAME = "p";

	private final List<Direction> directions;

	public Pawn(Position position, Team team) {
		super(position, team);
		directions = Direction.getPawnDirections(team);
	}

	@Override
	public String getName() {
		return NameUtils.parseName(NAME, team);
	}

	@Override
	public boolean isNeedCheckPath() {
		return false;
	}

	@Override
	public void validateMove(ChessPiece chessPiece) {
		Direction direction = moveManager.calculateDirection(chessPiece.position, directions);
		if (direction == UP && chessPiece.isMatchTeam(Team.BLANK)) {
			return;
		}
		if (isLeftUpOrRightUp(direction) && isNotBlank(chessPiece)) {
			return;
		}
		throw new IllegalArgumentException(MoveManager.CANNOT_MOVE_POSITION);
	}

	private boolean isNotBlank(ChessPiece chessPiece) {
		return chessPiece.isMatchTeam(Team.BLANK) == false;
	}

	private boolean isLeftUpOrRightUp(Direction direction) {
		return direction == RIGHT_UP || direction == LEFT_UP;
	}
}
