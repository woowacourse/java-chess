package chess.domain.chessPiece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static chess.domain.chessPiece.Role.*;

public abstract class Piece {
    protected Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public List<Position> getRoute(Position source, Position target){
        validateMovingRule(source, target);
        List<Position> route = new ArrayList<>();

        Direction direction = Direction.findDirection(source, target);
        Position movingPosition = direction.move(source);

        while (!movingPosition.equals(target)) {
            route.add(movingPosition);
            movingPosition = direction.move(movingPosition);
        }
        return Collections.unmodifiableList(route);
    }

    protected abstract void validateMovingRule(Position source, Position target);

    public abstract Role getRole();

    public boolean isTeam(Piece piece) {
        return team == piece.team;
    }

    public boolean isPawn() {
        return getRole() == BLACK_PAWN || getRole() == WHITE_PAWN;
    }

    public boolean isEmpty() {
        return getRole() == EMPTY;
    }
}
