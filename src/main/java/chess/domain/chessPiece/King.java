package chess.domain.chessPiece;

import chess.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chessPiece.Role.*;

public class King extends Piece {

    public King(Team team) {
        super(team);
    }

    //TODO : 리팩토링
    @Override
    public List<Position> getRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();
        validateMovingRule(source, target);

        Direction direction = Direction.findDirection(source, target);
        Position movingPosition = direction.move(source);

        while (!movingPosition.equals(target)) {
            route.add(movingPosition);
            movingPosition = direction.move(movingPosition);
        }
        return Collections.unmodifiableList(route);
    }

    @Override
    protected void validateMovingRule(Position source, Position target) {
        int rowDistance = source.calculateRowDistance(target);
        int columnDistance = source.calculateColumnDistance(target);
        if (rowDistance != 1 && columnDistance != 1) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public Role getRole() {
        if (team.isWhite()) {
            return WHITE_KING;
        }
        return BLACK_KING;
    }
}
