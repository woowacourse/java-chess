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
	private final Function<String, String> convertFrom;
	private final Function<Integer, RuleStrategy> pawnRuleStrategy;

	PieceColor(String color, Function<String, String> convertFrom, Function<Integer, RuleStrategy> pawnRuleStrategy) {
		this.color = color;
		this.convertFrom = convertFrom;
		this.pawnRuleStrategy = pawnRuleStrategy;
	}

	public static PieceColor of(String color) {
		return Arrays.stream(values())
			.filter(pieceColor -> pieceColor.color.equals(color))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("체스 색상이 존재하지 않습니다."));
	}

	public String convertFrom(String pieceName) {
		if (pieceName == null || pieceName.isEmpty()) {
			throw new IllegalArgumentException("체스 이름이 존재하지 않습니다.");
		}
		return convertFrom.apply(pieceName);
	}

	public RuleStrategy getPawnRuleStrategyBy(int movableRange) {
		return pawnRuleStrategy.apply(movableRange);
	}

	public String getColor() {
		return color;
	}

}
