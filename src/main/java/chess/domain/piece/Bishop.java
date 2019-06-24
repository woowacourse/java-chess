package chess.domain.piece;

import chess.domain.*;
import chess.domain.direction.Direction;
import chess.domain.direction.LeftDiagonalDirection;
import chess.domain.direction.RightDiagonalDirection;

import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {
    private final MoveRule rule;

    public Bishop(Aliance aliance, PieceValue pieceValue) {
        super(aliance, pieceValue);

        List<Direction> possibleDirections = Arrays.asList(RightDiagonalDirection.getInstance(), LeftDiagonalDirection.getInstance());
        this.rule = new MoveRule(possibleDirections, 7);
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

