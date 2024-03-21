package chess.domain.chessPiece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chessPiece.Role.BLACK_BISHOP;
import static chess.domain.chessPiece.Role.WHITE_BISHOP;

public class Bishop extends Piece{

    public Bishop(Team team) {
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
        if(rowDistance!=columnDistance){
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    @Override
    public Role getRole() {
        if(team.isWhite()) {
            return WHITE_BISHOP;
        }
        return BLACK_BISHOP;
    }
}
