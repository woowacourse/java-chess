package chess.domain.chesspiece;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;

public abstract class WorthlessPiece extends ChessPiece {

	private static final String NOT_SUPPORT_MESSAGE = "WorthlessPiece에서는 지원하지 않는 기능입니다.";

	public WorthlessPiece(Position position, Team team, String name) {
		super(position, team, name);
	}

	@Override
	public Positions makePathAndValidate(ChessPiece targetPiece) {
		throw new UnsupportedOperationException(NOT_SUPPORT_MESSAGE);
	}
}