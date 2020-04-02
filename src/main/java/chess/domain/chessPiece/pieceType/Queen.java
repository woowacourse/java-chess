package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.nonLeapableStrategy.QueenRuleStrategy;
import chess.domain.chessPiece.ChessPiece;

public class Queen extends ChessPiece {

    public static final String NAME = "Q";
    private static final int SCORE = 9;

    public Queen(PieceColor pieceColor) {
        super(pieceColor);
        ruleStrategy = new QueenRuleStrategy();
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

}
