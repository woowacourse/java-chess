package chess.domain.piece;

import chess.Calculator;
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

    private Pawn(Team team, boolean hasMoved) {
        super(team, hasMoved);
    }

    @Override
    public Piece move() {
        if (hasMoved) {
            return this;
        }
        return new Pawn(team, true);
    }

    @Override
    public boolean checkKind(Kind kind) {
        return Kind.PAWN == kind;
    }

    @Override
    public List<Position> findBetweenPositionsWhenAttack(Positions positions) {
        int rowDifference = positions.calculateRowDifference();
        int columnDifference = positions.calculateColumnDifference();
        validateAttackable(rowDifference, columnDifference);
        return findBetweenPositions(positions.source(), rowDifference, columnDifference);
    }

    private void validateAttackable(int rowDifference, int columnDifference) {
        if (isAttackable(rowDifference, columnDifference)) {
            return;
        }
        throw new IllegalArgumentException("해당 위치로 움직일 수 없습니다.");
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        List<Position> positions = new ArrayList<>();
        if (Math.abs(rowDifference) == START_MOVEMENT) {
            positions.add(position.move(Calculator.calculateSign(rowDifference), 0));
            return positions;
        }
        return new ArrayList<>();
    }

    @Override
    protected boolean isAttackable(int rowDifference, int columnDifference) {
        return rowDifference == NORMAL_MOVEMENT * team.attackDirection()
                && Math.abs(columnDifference) == ATTACK_COLUMN_MOVEMENT;
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        if (columnDifference != 0) {
            return false;
        }
        return rowDifference == NORMAL_MOVEMENT * team.attackDirection()
                || (!hasMoved && rowDifference == START_MOVEMENT * team.attackDirection());
    }
}
