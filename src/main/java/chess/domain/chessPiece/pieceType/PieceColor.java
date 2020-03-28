package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.BlackPawnRuleStrategy;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.WhitePawnRuleStrategy;

import java.util.function.Function;

public enum PieceColor {

    WHITE("화이트", String::toLowerCase, WhitePawnRuleStrategy::new),
    BLACK("블랙", String::toUpperCase, BlackPawnRuleStrategy::new);

    private final String color;
    private final Function<String, String> convertName;
    private final Function<Integer, RuleStrategy> pawnRuleStrategy;

    PieceColor(String color, Function<String, String> convertName, Function<Integer, RuleStrategy> pawnRuleStrategy) {
        this.color = color;
        this.convertName = convertName;
        this.pawnRuleStrategy = pawnRuleStrategy;
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
