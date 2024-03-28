package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.abstractPiece.AbstractPawn;
import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlackPawn extends AbstractPawn {
    private static final int MOVE_ROW_DIFFERENCE = -1;
    private static final int FIRST_MOVE_MAX_ROW_DIFFERENCE = -2;

    public BlackPawn() {
        super(Team.BLACK);
    }

    public BlackPawn(boolean isMoved) {
        this(new Character(Team.BLACK, Kind.PAWN), isMoved);
    }

    private BlackPawn(Character character, boolean hasMoved) {
        super(character, hasMoved);
    }

    @Override
    public Piece move() {
        if (isMoved) {
            return this;
        }
        return new BlackPawn(character, true);
    }

    @Override
    protected boolean isMovable(int rowDifference, int columnDifference) {
        if (columnDifference != 0) {
            return false;
        }
        return rowDifference == MOVE_ROW_DIFFERENCE
                || (!isMoved && rowDifference == FIRST_MOVE_MAX_ROW_DIFFERENCE);
    }

    @Override
    protected boolean isAttackable(int rowDifference, int columnDifference) {
        return rowDifference == MOVE_ROW_DIFFERENCE
                && Math.abs(columnDifference) == ATTACK_ABSOLUTE_COLUMN_DIFFERENCE;
    }

    @Override
    public Set<Position> findBetweenPositions(Movement movement) {
        if (movement.calculateRowDifference() == FIRST_MOVE_MAX_ROW_DIFFERENCE) {
            return new HashSet<>(List.of(movement.source().move(MOVE_ROW_DIFFERENCE, 0)));
        }
        return new HashSet<>();
    }
}
