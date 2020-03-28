package chess.domain.chessPiece.pieceState;

import chess.domain.RuleStrategy.RuleStrategy;
import chess.domain.chessPiece.pieceType.PieceColor;

public class MovedState extends PieceState {

    public MovedState(RuleStrategy ruleStrategy) {
        super(ruleStrategy);
    }

    @Override
    public State movedState(PieceColor pieceColor) {
        return this;
    }

}
