package chess.domain.piece.abstractPiece;

import chess.domain.Movement;
import chess.domain.piece.character.Character;
import chess.domain.piece.character.Kind;
import chess.domain.piece.character.Team;

public abstract class AbstractPawn extends Piece {
    protected static final int ATTACK_ABSOLUTE_COLUMN_DIFFERENCE = 1;

    public AbstractPawn(Team team) {
        this(new Character(team, Kind.PAWN), false);
    }

    protected AbstractPawn(Character character, boolean hasMoved) {
        super(character, hasMoved);
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

    protected abstract boolean isAttackable(int rowDifference, int columnDifference);
}
