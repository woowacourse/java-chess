package chess.domain.chessPiece.pieceType;

import java.util.function.Function;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.BlackPawnRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.WhitePawnRuleStrategy;

// TODO: 2020/03/26 Initial Position 또는 Promotion Position을 가질 수 있도록
// TODO: 2020/03/26 Pawn의 색에 따른 이동 규칙 전략을 변수로 가지도록 변경
public enum PieceColor {
	WHITE(String::toLowerCase, new WhitePawnRuleStrategy()),
	BLACK(String::toUpperCase, new BlackPawnRuleStrategy());

	private final Function<String, String> convertName;
	private final RuleStrategy pawnRuleStrategy;

	PieceColor(Function<String, String> convertName, RuleStrategy pawnRuleStrategy) {
		this.convertName = convertName;
		this.pawnRuleStrategy = pawnRuleStrategy;
	}

	public String convertName(String pieceName) {
		if (pieceName == null || pieceName.isEmpty()) {
			throw new IllegalArgumentException("체스 이름이 유효하지 않습니다.");
		}
		return convertName.apply(pieceName);
	}

	public RuleStrategy getPawnRuleStrategy() {
		return pawnRuleStrategy;
	}
}
