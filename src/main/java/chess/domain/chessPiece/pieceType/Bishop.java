package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.nonLeapableStrategy.BishopRule;
import chess.domain.chessPiece.ChessPiece;

public class Bishop extends ChessPiece {

    public static final String NAME = "B";
    private static final int SCORE = 3;

    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
        rule = new BishopRule();
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
