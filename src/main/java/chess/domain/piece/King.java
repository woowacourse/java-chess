package chess.domain.piece;

import chess.domain.*;
import chess.domain.direction.*;

import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private final MoveRule rule;

    public King(Aliance aliance, PieceValue pieceValue) {
        super(aliance, pieceValue);

        List<Direction> possibleDirections = Arrays.asList(VerticalDirection.getInstance(), HorizonDirection.getInstance(),
                                                            RightDiagonalDirection.getInstance(), LeftDiagonalDirection.getInstance());
        this.rule = new MoveRule(possibleDirections, 1);
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
