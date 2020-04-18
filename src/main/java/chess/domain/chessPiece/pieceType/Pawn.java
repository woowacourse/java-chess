package chess.domain.chessPiece.pieceType;

import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnRule;
import chess.domain.RuleStrategy.nonLeapableStrategy.pawnRuleStrategy.PawnState;
import chess.domain.chessPiece.ChessPiece;
import chess.domain.position.Position;

public class Pawn extends ChessPiece {

    public static final String NAME = "P";
    private static final int SCORE = 1;
    private static final char WHITE_INITIAL_STATE = '2';
    private static final char BLACK_INITIAL_STATE = '7';
    private static final int CHESS_FILE_INDEX = 1;

    private PawnState pawnState;

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
        this.pawnState = PawnState.initialState();
        rule = pieceColor.getPawnRuleStrategyBy();
    }

    public void pawnStateSelector(String position) {
        if (this.pieceColor.equals(PieceColor.WHITE) && position.charAt(CHESS_FILE_INDEX) == WHITE_INITIAL_STATE) {
            this.pawnState = PawnState.initialState();
            return;
        }
        if (this.pieceColor.equals(PieceColor.BLACK) && position.charAt(CHESS_FILE_INDEX) == BLACK_INITIAL_STATE) {
            this.pawnState = PawnState.initialState();
            return;
        }
        this.pawnState = PawnState.MovedState();
    }

    @Override
    public String getName() {
        return pieceColor.convertName(NAME);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        if (isInitialState()) {
            return canInitialMove(sourcePosition, targetPosition);
        }
        return rule.canMove(sourcePosition, targetPosition);
    }

    private boolean canInitialMove(Position sourcePosition, Position targetPosition) {
        if (((PawnRule) rule).canInitialMove(sourcePosition, targetPosition)) {
            switchedMovedState();
            return true;
        }
        return false;
    }

    public void switchedMovedState() {
        this.pawnState = pawnState.switchedMovedState();
    }

    public boolean isInitialState() {
        return pawnState.isInitialState();
    }
}
