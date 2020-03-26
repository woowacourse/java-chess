package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.RuleStrategy;

public class Knight extends PieceType {

	public static final String NAME = "N";

	public Knight(PieceColor pieceColor, RuleStrategy ruleStrategy) {
		super(pieceColor, ruleStrategy);
	}

	@Override
	public String getName() {
		return pieceColor.convertName(NAME);
	}
}
