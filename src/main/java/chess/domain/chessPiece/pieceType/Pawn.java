package chess.domain.chessPiece.pieceType;

import chess.domain.chessPiece.ChessPiece;

public class Pawn extends ChessPiece {

    public static final String NAME = "P";
    private static final int SCORE = 1;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
        rule = pieceColor.getPawnRuleStrategyBy();
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
