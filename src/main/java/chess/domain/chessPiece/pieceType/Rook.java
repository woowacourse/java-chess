package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.nonLeapableStrategy.RookRule;
import chess.domain.chessPiece.ChessPiece;

public class Rook extends ChessPiece {

    public static final String NAME = "R";
    public static final int SCORE = 5;

    public Rook(PieceColor pieceColor) {
        super(pieceColor);
        rule = new RookRule();
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
