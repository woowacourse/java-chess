package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.RuleStrategy;

public class Rook extends PieceType {

	public static final String NAME = "R";

	public Rook(PieceColor pieceColor, RuleStrategy ruleStrategy) {
		super(pieceColor, ruleStrategy);
	}

	@Override
	public String getName() {
		return pieceColor.convertName(NAME);
	}
}
