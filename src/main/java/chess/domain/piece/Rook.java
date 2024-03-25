package chess.domain.piece;

import chess.domain.Calculator;
import chess.domain.Position;
import chess.domain.Positions;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Team team) {
        this(team, false);
    }

    private Rook(Team team, boolean isMoved) {
        super(team, isMoved);
    }

    @Override
    public Piece move() {
        if (isMoved) {
            return this;
        }
        return new Rook(team, true);
    }

    @Override
    public boolean checkKind(Kind kind) {
        return Kind.ROOK == kind;
    }

    @Override
    public boolean isAttackable(Positions positions) {
        return isMovable(positions);
    }

    @Override
    public boolean isMovable(Positions positions) {
        return positions.calculateRowDifference() == 0 || positions.calculateColumnDifference() == 0;
    }

    @Override
    public List<Position> findBetweenPositionsWhenAttack(Positions positions) {
        return findBetweenPositions(positions);
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        int absoluteDifference = Math.max(Math.abs(rowDifference), Math.abs(columnDifference));
        int rowSign = Calculator.calculateMinMovement(rowDifference);
        int columnSign = Calculator.calculateMinMovement(columnDifference);

        List<Position> positions = new ArrayList<>();
        for (int i = MIN_MOVEMENT; i < absoluteDifference; i++) {
            positions.add(position.move(rowSign * i, columnSign * i));
        }
        return positions;
    }

}
