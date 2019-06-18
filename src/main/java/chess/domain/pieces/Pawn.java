package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    private List<Direction> moveDirection;
    private List<Direction> attackDirection;

    public Pawn(ChessTeam team) {
        super("pawn", team);
        moveDirection = new ArrayList<>();
        moveDirection.add(new Direction(0, 1 * team.color()));
        moveDirection.add(new Direction(0, 2 * team.color()));

        attackDirection = new ArrayList<>();
        attackDirection.add(new Direction(1, 1 * team.color()));
        attackDirection.add(new Direction(-1, 1 * team.color()));
    }

    @Override
    public Direction move(Point p1, Point p2) {
        Direction direction = p1.direction(p2);
        if (moveDirection.contains(direction)) {
            initialMove();
            return direction;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Direction attack(Point p1, Point p2) {
        Direction direction = p1.direction(p2);
        if (attackDirection.contains(direction)) {
            initialMove();
            return direction;
        }
        throw new IllegalArgumentException();
    }

    private void initialMove() {
        if (moveDirection.size() == 2) {
            moveDirection.remove(1);
        }
    }

    @Override
    public String toString() {
        return "P";
    }
}
