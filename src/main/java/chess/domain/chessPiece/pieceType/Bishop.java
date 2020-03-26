package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.RuleStrategy;

public class Bishop extends PieceType {

	public static final String NAME = "B";

	public Bishop(PieceColor pieceColor, RuleStrategy ruleStrategy) {
		super(pieceColor, ruleStrategy);
	}

	@Override
	public String getName() {
		return pieceColor.convertName(NAME);
	}
}
