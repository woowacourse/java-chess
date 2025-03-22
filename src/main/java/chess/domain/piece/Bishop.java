package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    public Bishop(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    public boolean availablePath(Position start, Position target) {
        return start.onDiagonal(target);
    }

    @Override
    public List<Position> findAllRouteToTarget(Position start, Position target) {
        List<Position> positions = new ArrayList<>();

        Position current = start;
        int rowStep = (target.rowValue() - start.rowValue()) / Math.abs(target.rowValue() - start.rowValue());
        int colStep = (target.colValue() - start.colValue()) / Math.abs(target.colValue() - start.colValue());

        while (!current.equals(target)) {
            current = current.moveVertical(rowStep).moveHorizontal(colStep);
            positions.add(current);
        }

        return positions;
    }

    @Override
    public boolean canMove(List<Piece> piecesOnRoute, Position start, Position target) {
        if(piecesOnRoute.size() != 1){
            return false;
        }
        return piecesOnRoute.getLast().isEmpty() || this.isOtherTeam(piecesOnRoute.getLast());
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
