package chess.domain.chesspiece;

import chess.domain.utils.NameUtils;
import chess.domain.position.Position;
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
