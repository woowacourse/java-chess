package chess.domain.piece;

import chess.domain.Calculator;
import chess.domain.Position;
import chess.domain.Positions;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public static final int NORMAL_MOVEMENT = 1;
    public static final int START_MOVEMENT = 2;
    public static final int ATTACK_COLUMN_MOVEMENT = 1;

    public Pawn(Team team) {
        this(team, false);
    }

    private Pawn(Team team, boolean isMoved) {
        super(team, isMoved);
    }

    @Override
    public Piece move() {
        if (isMoved) {
            return this;
        }
        return new Pawn(team, true);
    }

    @Override
    public boolean checkKind(Kind kind) {
        return Kind.PAWN == kind;
    }

    @Override
    public boolean isAttackable(Positions positions) {
        return positions.calculateRowDifference() == NORMAL_MOVEMENT * team.attackDirection()
                && Math.abs(positions.calculateColumnDifference()) == ATTACK_COLUMN_MOVEMENT;
    }

    @Override
    public boolean isMovable(Positions positions) {
        if (positions.calculateColumnDifference() != 0) {
            return false;
        }

        int rowDifference = positions.calculateRowDifference();
        return rowDifference == NORMAL_MOVEMENT * team.attackDirection()
                || (!isMoved && rowDifference == START_MOVEMENT * team.attackDirection());
    }

    @Override
    public List<Position> findBetweenPositionsWhenAttack(Positions positions) {
        int rowDifference = positions.calculateRowDifference();
        int columnDifference = positions.calculateColumnDifference();

        validateAttackable(positions);
        return findBetweenPositions(positions.source(), rowDifference, columnDifference);
    }

    private void validateAttackable(Positions positions) {
        if (isAttackable(positions)) {
            return;
        }
        throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        List<Position> positions = new ArrayList<>();
        if (Math.abs(rowDifference) == START_MOVEMENT) {
            positions.add(position.move(Calculator.calculateMinMovement(rowDifference), 0));
            return positions;
        }
        return new ArrayList<>();
    }
}
