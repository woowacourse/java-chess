package chess.domain.chesspiece;

import chess.domain.position.Position;
import chess.domain.position.Positions;

public class Blank extends ChessPiece {
	private static final String NAME = ".";
	private static final String NOT_SUPPORT_MESSAGE = "BLANK에서는 지원하지 않는 기능입니다.";

	public Blank(Position position) {
		super(position, null);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean isNotNeedCheckPath() {
		throw new UnsupportedOperationException(NOT_SUPPORT_MESSAGE);
	}

	@Override
	public Positions makePathAndValidate(ChessPiece targetPiece) {
		throw new UnsupportedOperationException(NOT_SUPPORT_MESSAGE);
	}

	@Override
	public void validateCanGo(ChessPiece targetPiece) {
		throw new UnsupportedOperationException(NOT_SUPPORT_MESSAGE);
	}

}