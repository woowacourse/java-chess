package chess.domain.chesspiece;

import chess.domain.Team;
import chess.domain.position.Position;

public abstract class ValuablePiece extends ChessPiece {

	private static final String NOT_SUPPORT_MESSAGE = "ValuablePiece에서 지원하지 않는 기능입니다.";

	public ValuablePiece(Position position, Team team, String name) {
		super(position, team, name);
	}

	@Override
	public void validateCanGo(ChessPiece targetPiece) {
		throw new UnsupportedOperationException(NOT_SUPPORT_MESSAGE);
	}
}
