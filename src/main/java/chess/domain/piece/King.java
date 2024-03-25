package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Positions;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private static final int MAX_MOVE_DIFFERENCE = 1;

    public King(Team team) {
        this(team, false);
    }

    private King(Team team, boolean isMoved) {
        super(team, isMoved);
    }

    @Override
    public Piece move() {
        if (isMoved) {
            return this;
        }
        return new King(team, true);
    }

    @Override
    public boolean checkKind(Kind kind) {
        return Kind.KING == kind;
    }

    @Override
    public boolean isAttackable(Positions positions) {
        return isMovable(positions);
    }

    @Override
    public boolean isMovable(Positions positions) {
        return Math.abs(positions.calculateRowDifference()) <= MAX_MOVE_DIFFERENCE
                && Math.abs(positions.calculateColumnDifference()) <= MAX_MOVE_DIFFERENCE;
    }

    @Override
    public List<Position> findBetweenPositionsWhenAttack(Positions positions) {
        return findBetweenPositions(positions);
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        return new ArrayList<>();
    }
}
