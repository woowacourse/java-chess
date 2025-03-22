package chess.domain.piece;

import static chess.domain.Movement.DOWN_DOWN_LEFT;
import static chess.domain.Movement.DOWN_DOWN_RIGHT;
import static chess.domain.Movement.LEFT_LEFT_DOWN;
import static chess.domain.Movement.LEFT_LEFT_UP;
import static chess.domain.Movement.RIGHT_RIGHT_DOWN;
import static chess.domain.Movement.RIGHT_RIGHT_UP;
import static chess.domain.Movement.UP_UP_LEFT;
import static chess.domain.Movement.UP_UP_RIGHT;

import chess.domain.Column;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.TeamColor;
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
        super(teamColor, PieceType.KNIGHT);
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
        return List.of(position, target);
    }

    @Override
    public boolean canMove(List<Piece> piecesOnRoute, Position start, Position target) {
        boolean empty = piecesOnRoute.getLast().isEmpty();
        boolean otherTeam = this.isOtherTeam(piecesOnRoute.getLast());

        return piecesOnRoute.getLast().isEmpty() || this.isOtherTeam(piecesOnRoute.getLast());
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
