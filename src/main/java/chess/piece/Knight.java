package chess.piece;

import static chess.Movement.DOWN_DOWN_LEFT;
import static chess.Movement.DOWN_DOWN_RIGHT;
import static chess.Movement.LEFT_LEFT_DOWN;
import static chess.Movement.LEFT_LEFT_UP;
import static chess.Movement.RIGHT_RIGHT_DOWN;
import static chess.Movement.RIGHT_RIGHT_UP;
import static chess.Movement.UP_UP_LEFT;
import static chess.Movement.UP_UP_RIGHT;

import chess.Column;
import chess.Movement;
import chess.Position;
import chess.Row;
import chess.TeamColor;
import java.util.List;

public class Knight extends Piece{
    private final static List<Movement> knightMovement =
            List.of(LEFT_LEFT_UP,
                    LEFT_LEFT_DOWN,
                    RIGHT_RIGHT_UP,
                    RIGHT_RIGHT_DOWN,
                    DOWN_DOWN_LEFT,
                    DOWN_DOWN_RIGHT,
                    UP_UP_LEFT,
                    UP_UP_RIGHT
            );

    private final List<Movement> availableMovement;

    public Knight(TeamColor teamColor) {
        super(teamColor);
        this.availableMovement = knightMovement;
    }

    public boolean availablePath(Position start, Position target) {
        for (Movement movement : availableMovement) {
            if(!start.canMove(movement)) {
                continue;
            }

            Position moved = start.move(movement);
            if(moved.equals(target)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {
        Row row = Row.from((start.rowValue() + target.rowValue()) / 2);
        Column column = Column.from((start.colValue() + target.colValue()) / 2);

        Position position = new Position(row, column);
        return List.of(position);
    }

    @Override
    public boolean canMove(Piece targetPiece, Position start, Position target) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
