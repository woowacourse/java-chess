package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Positions;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Team team) {
        this(team, false);
    }

    private Bishop(Team team, boolean hasMoved) {
        super(team, hasMoved);
    }

    @Override
    public Piece move() {
        if (hasMoved) {
            return this;
        }
        return new Bishop(team, true);
    }

    @Override
    public boolean checkKind(Kind kind) {
        return Kind.BISHOP == kind;
    }

    @Override
    public boolean isAttackable(Positions positions) {
        return isMovable(positions);
    }

    @Override
    public boolean isMovable(Positions positions) {
        return Math.abs(positions.calculateRowDifference()) == Math.abs(positions.calculateColumnDifference());
    }

    @Override
    public List<Position> findBetweenPositionsWhenAttack(Positions positions) {
        return findBetweenPositions(positions);
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        int absoluteDifference = Math.abs(rowDifference);
        int rowSign = rowDifference / absoluteDifference;
        int columnSign = columnDifference / absoluteDifference;

        List<Position> positions = new ArrayList<>();
        for (int movement = MIN_MOVEMENT; movement < absoluteDifference; movement++) {
            positions.add(position.move(rowSign * movement, columnSign * movement));
        }
        return positions;
    }
}
