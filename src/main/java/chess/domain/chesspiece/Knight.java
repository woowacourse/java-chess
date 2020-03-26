package chess.domain.chesspiece;

import chess.domain.NameUtils;
import chess.domain.Position;
import chess.domain.PositionGap;
import chess.domain.Team;

public class Knight extends WorthlessPiece {
	private static final String NAME = "n";

	public Knight(Position position, Team team) {
		super(position, team);
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
		moveManager.validateKnightMove(chessPiece.position);
	}
}
