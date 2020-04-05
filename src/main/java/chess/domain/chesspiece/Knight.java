package chess.domain.chesspiece;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.utils.NameUtils;

public class Knight extends WorthlessPiece {
	private static final String NAME = "n";

	public Knight(Position position, Team team) {
		super(position, team, NAME);
	}

	@Override
	public boolean isNotNeedCheckPath() {
		return true;
	}

	@Override
	public void validateCanGo(ChessPiece targetPiece) {
		moveManager.validateKnightMove(targetPiece.position);
	}
}