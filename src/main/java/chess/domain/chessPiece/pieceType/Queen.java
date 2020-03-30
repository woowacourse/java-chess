package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.nonLeapableStrategy.QueenRuleStrategy;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.InitialState;

public class Queen extends ChessPiece {

    public static final String NAME = "Q";
    private static final int SCORE = 9;

    public Queen(PieceColor pieceColor) {
        super(pieceColor);
        state = new InitialState(new QueenRuleStrategy());
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
