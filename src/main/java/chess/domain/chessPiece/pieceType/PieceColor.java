package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.Rule;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.BlackPawnRule;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.WhitePawnRule;
import chess.domain.chessPiece.ChessPiece;

import java.util.function.Function;
import java.util.function.Supplier;

public enum PieceColor {

    WHITE(String::toLowerCase, WhitePawnRule::new),
    BLACK(String::toUpperCase, BlackPawnRule::new);

    private final Function<String, String> convertName;
    private final Supplier<Rule> pawnRuleStrategy;

    PieceColor(Function<String, String> convertName, Supplier<Rule> pawnRuleStrategy) {
        this.convertName = convertName;
        this.pawnRuleStrategy = pawnRuleStrategy;
    }

    public void validatePlayerTurn(ChessPiece chessPiece) {
        if (!chessPiece.isSamePieceColorWith(this)) {
            throw new IllegalArgumentException("해당 플레이어 턴이 아닙니다.");
        }
    }

    public String convertName(String pieceName) {
        if (pieceName == null || pieceName.isEmpty()) {
            throw new IllegalArgumentException("체스 이름이 유효하지 않습니다.");
        }
        return convertName.apply(pieceName);
    }

    public Rule getPawnRuleStrategyBy() {
        return pawnRuleStrategy.get();
    }

    public PieceColor getOppositeColor() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
