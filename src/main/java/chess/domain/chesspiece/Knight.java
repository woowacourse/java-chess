package chess.domain.chesspiece;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.utils.NameUtils;

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
	public void validateCanGo(ChessPiece targetPiece) {
		moveManager.validateKnightMove(targetPiece.position);
	}
}
