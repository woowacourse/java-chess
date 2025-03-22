package chess.piece;

import chess.Position;
import chess.TeamColor;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public boolean availablePath(Position start, Position target) {
        return start.onStraight(target);
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {
        List<Position> positions = new ArrayList<>();

        Position current = new Position(start.row(), start.column());

        while (!current.equals(target)) {
            if (start.onSameRow(target)) {
                if (current.column().intValue() < target.column().intValue()) {
                    current = current.moveRight();
                } else {
                    current = current.moveLeft();
                }
            }
            if (start.onSameColumn(target)) {
                if (current.row().intValue() < target.row().intValue()) {
                    current = current.moveUp();
                } else {
                    current = current.moveDown();
                }
            }
            positions.add(current);
        }
        return positions;
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
