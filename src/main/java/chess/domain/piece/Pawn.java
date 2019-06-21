package chess.domain.piece;

import chess.domain.*;
import chess.domain.direction.Direction;
import chess.domain.direction.LeftDiagonalDirection;
import chess.domain.direction.RightDiagonalDirection;
import chess.domain.direction.VerticalDirection;

import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    private static final String NAME = "P";
    private static final double SCORE = 1;

    private final MoveRule rule;
    private final MoveRule firstRule;
    private final MoveRule attactRule;

    public Pawn(Aliance aliance) {
        super(aliance);

        List<Direction> possibleDirections = Arrays.asList(VerticalDirection.getInstance());
        List<Direction> attackDirections = Arrays.asList(RightDiagonalDirection.getInstance(), LeftDiagonalDirection.getInstance());
        this.rule = new MoveRule(possibleDirections, 1);
        this.firstRule = new MoveRule(possibleDirections,2);
        this.attactRule = new MoveRule(attackDirections,1);
    }

    @Override
    public void checkPossibleMove(Board board, Position startPosition, Navigator navigator) {
        if (isFirstMove(startPosition)) {
            firstRule.judge(navigator);
            return;
        }

        if (isAttackMove(board, startPosition)) {
            attactRule.judge(navigator);
            return;
        }

        rule.judge(navigator);
    }

    private boolean isFirstMove(Position startPosition) {
        if (this.aliance == Aliance.WHITE) {
            return startPosition.toString().charAt(1) == '2';
        }
        return startPosition.toString().charAt(1) == '7';
    }

    private boolean isAttackMove(Board board, Position startPosition) {
        Position attackablePosition1 = startPosition.movePosition(1,1);
        Position attackablePosition2 = startPosition.movePosition(1,-1);

        return (board.pieceValueOf(attackablePosition1.toString()) != null)
                || (board.pieceValueOf(attackablePosition2.toString()) != null);
    }

    @Override
    public String toString() {
        if (aliance == Aliance.BLACK) {
            return NAME;
        }
        return NAME.toLowerCase();
    }
}

