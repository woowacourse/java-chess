package chess.domain.piece;

import chess.domain.*;
import chess.domain.direction.*;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    private static final int LIMIT_MOVE_COUNT = 1;

    private final MoveRule rule;

    public Knight(Aliance aliance, PieceValue pieceValue) {
        super(aliance, pieceValue);

        List<Direction> possibleDirections = Arrays.asList(HorizonRightKnightDirection.getInstance(), HorizonLeftKnightDirection.getInstance(),
                VerticalRightKnightDirection.getInstance(), VerticalLeftKnightDirection.getInstance());
        this.rule = new MoveRule(possibleDirections, LIMIT_MOVE_COUNT);
    }

    @Override
    public void checkPossibleMove(Board board, Position position, Navigator navigator) {
        rule.judge(navigator);
    }

    @Override
    public String toString() {
        if (aliance == Aliance.BLACK) {
            return pieceValue.getName();
        }
        return pieceValue.getName().toLowerCase();
    }
}

