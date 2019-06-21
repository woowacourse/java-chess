package chess.domain.piece;

import chess.domain.*;
import chess.domain.direction.*;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    private static final String NAME = "N";
    private static final double SCORE = 2.5;

    private final MoveRule rule;

    public Knight(Aliance aliance) {
        super(aliance);

        List<Direction> possibleDirections = Arrays.asList(HorizonRightKnightDirection.getInstance(), HorizonLeftKnightDirection.getInstance(),
                                                            VerticalRightKnightDirection.getInstance(), VerticalLeftKnightDirection.getInstance());
        this.rule = new MoveRule(possibleDirections, 1);
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

