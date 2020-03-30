package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.nonLeapableStrategy.BishopRuleStrategy;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.InitialState;

public class Bishop extends ChessPiece {

    public static final String NAME = "B";
    private static final int SCORE = 3;

    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
        state = new InitialState(new BishopRuleStrategy());
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
