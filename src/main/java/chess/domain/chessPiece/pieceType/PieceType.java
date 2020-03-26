package chess.domain.chessPiece.pieceType;

import java.util.Objects;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRuleStrategy;
import chess.domain.position.Position;

public abstract class PieceType {

	protected final PieceColor pieceColor;
	protected final RuleStrategy ruleStrategy;

	public PieceType(PieceColor pieceColor, RuleStrategy ruleStrategy) {
		Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");
		Objects.requireNonNull(ruleStrategy, "피스 전략이 null입니다.");
		this.pieceColor = pieceColor;
		this.ruleStrategy = ruleStrategy;
	}

	public boolean canMove(Position source, Position target) {
		return ruleStrategy.canMove(source, target);
	}

	public boolean canLeap() {
		return ruleStrategy.canLeap();
	}

	public boolean canCatch(Position source, Position target) {
		if (this instanceof Pawn) {
			return ((PawnRuleStrategy)ruleStrategy).canMoveToCatch(source, target);
		}
		return ruleStrategy.canMove(source, target);
	}

	public boolean isSamePieceColorWith(PieceType pieceType) {
		return this.pieceColor.equals(pieceType.pieceColor);
	}

	public abstract String getName();
}
