package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
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
        if (isMoved) {
            return this;
        }
        return new Pawn(character, true);
    }

    @Override
    public boolean isMovable(Movement movement, boolean isAttack) {
        int rowDifference = movement.calculateRowDifference();
        int columnDifference = movement.calculateColumnDifference();
        if (isAttack) {
            return isAttackable(rowDifference, columnDifference);
        }
        return isMovable(rowDifference, columnDifference);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        if (columnDifference != 0) {
            return false;
        }
        if (character.team() == Team.WHITE) {
            return rowDifference == WHITE_MOVE_ROW_DIFFERENCE
                    || (!isMoved && rowDifference == WHITE_FIRST_MOVE_MAX_ROW_DIFFERENCE);
        }
        return rowDifference == BLACK_MOVE_ROW_DIFFERENCE
                || (!isMoved && rowDifference == BLACK_FIRST_MOVE_MAX_ROW_DIFFERENCE);
    }

    private boolean isAttackable(int rowDifference, int columnDifference) {
        if (character.team() == Team.WHITE) {
            return rowDifference == WHITE_MOVE_ROW_DIFFERENCE
                    && Math.abs(columnDifference) == ATTACK_ABSOLUTE_COLUMN_DIFFERENCE;
        }
        return rowDifference == BLACK_MOVE_ROW_DIFFERENCE
                && Math.abs(columnDifference) == ATTACK_ABSOLUTE_COLUMN_DIFFERENCE;
    }

    @Override
    public List<Position> findBetweenPositions(Movement movement, Boolean isAttack) {
        if (isAttack) {
            return new ArrayList<>();
        }
        return findBetweenPositions(movement);
    }
}
