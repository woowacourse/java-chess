package chess.domain.piece;

import chess.Calculator;
import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import chess.exception.ImpossibleMoveException;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public static final int WHITE_MOVE_ROW_DIFFERENCE = 1;
    public static final int WHITE_FIRST_MOVE_MAX_ROW_DIFFERENCE = 2;
    public static final int BLACK_MOVE_ROW_DIFFERENCE = -1;
    public static final int BLACK_FIRST_MOVE_MAX_ROW_DIFFERENCE = -2;
    public static final int ATTACK_ABSOLUTE_COLUMN_DIFFERENCE = 1;

    public Pawn(Team team) {
        this(new Character(team, Kind.PAWN), false);
    }

    private Pawn(Character character, boolean hasMoved) {
        super(character, hasMoved);
    }

    @Override
    public Piece move() {
        if (hasMoved) {
            return this;
        }
        return new Pawn(character, true);
    }

    @Override
    public List<Position> findBetweenPositionsWhenAttack(Movement movement) {
        int rowDifference = movement.calculateRowDifference();
        int columnDifference = movement.calculateColumnDifference();
        validateAttackable(rowDifference, columnDifference);
        return findBetweenPositions(movement.source(), rowDifference, columnDifference);
    }

    private void validateAttackable(int rowDifference, int columnDifference) {
        if (isAttackable(rowDifference, columnDifference)) {
            return;
        }
        throw new ImpossibleMoveException("해당 위치로 움직일 수 없습니다.");
    }

    @Override
    protected List<Position> findBetweenPositions(Position position, int rowDifference, int columnDifference) {
        List<Position> positions = new ArrayList<>();
        if (Math.abs(rowDifference) == WHITE_FIRST_MOVE_MAX_ROW_DIFFERENCE) {
            positions.add(position.move(Calculator.divideAbsoluteValue(rowDifference), 0));
        }
        return positions;
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        if (columnDifference != 0) {
            return false;
        }
        if (character.team() == Team.WHITE) {
            return rowDifference == WHITE_MOVE_ROW_DIFFERENCE
                    || (!hasMoved && rowDifference == WHITE_FIRST_MOVE_MAX_ROW_DIFFERENCE);
        }
        return rowDifference == BLACK_MOVE_ROW_DIFFERENCE
                || (!hasMoved && rowDifference == BLACK_FIRST_MOVE_MAX_ROW_DIFFERENCE);
    }

    @Override
    protected boolean isAttackable(int rowDifference, int columnDifference) {
        if (character.team() == Team.WHITE) {
            return rowDifference == WHITE_MOVE_ROW_DIFFERENCE
                    && Math.abs(columnDifference) == ATTACK_ABSOLUTE_COLUMN_DIFFERENCE;
        }
        return rowDifference == BLACK_MOVE_ROW_DIFFERENCE
                && Math.abs(columnDifference) == ATTACK_ABSOLUTE_COLUMN_DIFFERENCE;
    }
}
