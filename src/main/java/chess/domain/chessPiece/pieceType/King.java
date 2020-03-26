package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.RuleStrategy;

public class King extends PieceType {

	public static final String NAME = "K";

	public King(PieceColor pieceColor, RuleStrategy ruleStrategy) {
		super(pieceColor, ruleStrategy);
	}

	@Override
	public String getName() {
		return pieceColor.convertName(NAME);
	}

}
