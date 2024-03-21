package chess.domain.chessPiece;

import chess.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chessPiece.Role.*;

public class Queen extends Piece {

    public Queen(Team team) {
        super(team);
    }
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
        if(source.isDifferentRow(target)&&source.isDifferentColumn(target)&&rowDistance!=columnDistance){
            throw new IllegalArgumentException("x");
        }
    }

    @Override
    public Role getRole() {
        if(team.isWhite()) {
            return WHITE_QUEEN;
        }
        return BLACK_QUEEN;
    }
}
