package chess.domain.chessPiece.pieceType;

import java.util.Arrays;
import java.util.function.Function;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.BlackPawnRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.WhitePawnRuleStrategy;

public enum PieceColor {

	WHITE("white", String::toLowerCase, WhitePawnRuleStrategy::new),
	BLACK("black", String::toUpperCase, BlackPawnRuleStrategy::new);

	private final String color;
	private final Function<String, String> convertName;
	private final Function<Integer, RuleStrategy> pawnRuleStrategy;

	PieceColor(String color, Function<String, String> convertName, Function<Integer, RuleStrategy> pawnRuleStrategy) {
		this.color = color;
		this.convertName = convertName;
		this.pawnRuleStrategy = pawnRuleStrategy;
	}

	public static PieceColor of(String color) {
		return Arrays.stream(values())
			.filter(pieceColor -> pieceColor.color.equals(color))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 색상의 피스입니다."));
	}

	public String convertName(String pieceName) {
		if (pieceName == null || pieceName.isEmpty()) {
			throw new IllegalArgumentException("체스 이름이 유효하지 않습니다.");
		}
		return convertName.apply(pieceName);
	}

	public RuleStrategy getPawnRuleStrategyBy(int movableRange) {
		return pawnRuleStrategy.apply(movableRange);
	}

	public String getColor() {
		return color;
	}
}
