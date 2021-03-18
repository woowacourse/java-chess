package chess.domain.piece;

import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private static final String PAWN_NAME = "P";

    public Pawn(Team team) {
        super(PAWN_NAME, team);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();
        Direction direction = findDirection();

        if (isFirstStep(target)) {
            movablePositions.add(Position.of(target.getHorizontal(),
                    Vertical.findFromWeight(target.getVerticalWeight() + direction.getY() * 2)));
        }
        movablePositions.add(Position.of(target.getHorizontal(),
                Vertical.findFromWeight(target.getVerticalWeight() + direction.getY())));

        return movablePositions;
    }

    private Direction findDirection() {
        if (isBlack()) {
            return Direction.BOTTOM;
        }
        return Direction.TOP;
    }

    private boolean isFirstStep(Position position) {
        if (isBlack() && position.isSameVertical(Vertical.SEVEN)) {
            return true;
        }
        return isWhite() && position.isSameVertical(Vertical.TWO);
    }
}

/*
1. 팀
2. 첫 무빙이냐
 */