package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.chessPiece.PieceColor;

public class Pawn extends PieceType {

    public static final String NAME = "P";

    public Pawn(PieceColor pieceColor, RuleStrategy ruleStrategy) {
        super(pieceColor, ruleStrategy);
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }
}
