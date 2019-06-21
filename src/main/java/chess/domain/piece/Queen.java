package chess.domain.piece;

import chess.domain.*;
import chess.domain.direction.*;

import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {
    private static final String NAME = "Q";
    private static final double SCORE = 9;

    private final MoveRule rule;

    public Queen(Aliance aliance) {
        super(aliance);

        List<Direction> possibleDirections = Arrays.asList(VerticalDirection.getInstance(), HorizonDirection.getInstance(),
                RightDiagonalDirection.getInstance(), LeftDiagonalDirection.getInstance());
        this.rule = new MoveRule(possibleDirections, 7);
    }

    @Override
    public void checkPossibleMove(Board board, Position position, Navigator navigator) {
        rule.judge(navigator);
    }

    @Override
    public String toString() {
        if (aliance == Aliance.BLACK) {
            return NAME;
        }
        return NAME.toLowerCase();
    }
}

