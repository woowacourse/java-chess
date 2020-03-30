package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.KingRuleStrategy;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceState.InitialState;

public class King extends ChessPiece {

    public static final String NAME = "K";
    private static final int SCORE = 0;

    public King(PieceColor pieceColor) {
        super(pieceColor);
        state = new InitialState(new KingRuleStrategy());
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
