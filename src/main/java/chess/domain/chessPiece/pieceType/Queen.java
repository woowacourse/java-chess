package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.RuleStrategy;

public class Queen extends PieceType {

	public static final String NAME = "Q";

	public Queen(PieceColor pieceColor, RuleStrategy ruleStrategy) {
		super(pieceColor, ruleStrategy);
	}

	@Override
	public String getName() {
		return pieceColor.convertName(NAME);
	}
}
