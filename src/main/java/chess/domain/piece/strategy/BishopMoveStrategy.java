package chess.domain.piece.strategy;

import chess.domain.board.Direction;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import java.util.List;


public class BishopMoveStrategy extends MoveStrategy {

    @Override
    public boolean isValidateCanMove(Color color, Position from, Position to) {
        List<Direction> directions = bishopDirection(color);

        if (isInvalidDirection(from, to, directions)) {
            throw new IllegalArgumentException("비숍이 이동할 수 없는 위치입니다.");
        }

        return true;
    }

    public static List<Direction> bishopDirection(Color color) {
        return Direction.getColorDirections(color, List.of(Direction.TOPLEFT, Direction.TOPRIGHT, Direction.DOWNLEFT, Direction.DOWNRIGHT));
    }
}
