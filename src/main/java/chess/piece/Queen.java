package chess.piece;

import chess.Position;
import chess.TeamColor;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public boolean availablePath(Position start, Position target) {
        return start.onStraight(target) || start.onDiagonal(target);
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {
        List<Position> positions = new ArrayList<>();
        Position current = start;

        if (start.onSameColumn(target)) {
            int oneStep = (target.rowValue() - start.rowValue()) / Math.abs(target.rowValue() - start.rowValue());
            while (!current.equals(target)) {
                current = current.moveVertical(oneStep);
                positions.add(current);
            }
        }
        if (start.onSameRow(target)) {
            int oneStep = (target.colValue() - start.colValue()) / Math.abs(target.colValue() - start.colValue());
            while (!current.equals(target)) {
                current = current.moveHorizontal(oneStep);
                positions.add(current);
            }
        }
        if (start.onDiagonal(target)) {
            int rowStep = (target.rowValue() - start.rowValue()) / Math.abs(target.rowValue() - start.rowValue());
            int colStep = (target.colValue() - start.colValue()) / Math.abs(target.colValue() - start.colValue());

            while (!current.equals(target)) {
                current = current.moveVertical(rowStep).moveHorizontal(colStep);
                positions.add(current);
            }
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
