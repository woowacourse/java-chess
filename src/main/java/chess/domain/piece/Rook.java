package chess.domain.piece;

import chess.domain.*;
import chess.domain.direction.Direction;
import chess.domain.direction.HorizonDirection;
import chess.domain.direction.VerticalDirection;

import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {
    private static final int LIMIT_MOVE_COUNT = 7;

    private final MoveRule rule;

    public Rook(Aliance aliance, PieceValue pieceValue) {
        super(aliance, pieceValue);

        List<Direction> possibleDirections = Arrays.asList(VerticalDirection.getInstance(), HorizonDirection.getInstance());
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

