package chess.piece;

import chess.position.UnitMovement;
import chess.score.PieceScore;
import java.util.Set;

public abstract class Pawn extends Piece {

    private static final Set<UnitMovement> WHITE_PAWN_UNIT_MOVEMENT = Set.of(
            UnitMovement.differencesOf(0, 1)
    );
    private static final Set<UnitMovement> WHITE_PAWN_ATTACK_UNIT_MOVEMENT = Set.of(
            UnitMovement.differencesOf(1, 1),
            UnitMovement.differencesOf(-1, 1)
    );
    private static final Set<UnitMovement> BLACK_PAWN_UNIT_MOVEMENT = Set.of(
            UnitMovement.differencesOf(0, -1)
    );
    private static final Set<UnitMovement> BLACK_PAWN_ATTACK_UNIT_MOVEMENT = Set.of(
            UnitMovement.differencesOf(1, -1),
            UnitMovement.differencesOf(-1, -1)
    );

    protected Pawn(Color color) {
        super(color, PieceScore.PAWN, getPawnDirectionByColor(color));
    }

    private static Set<UnitMovement> getPawnDirectionByColor(Color color) {
        if (color == Color.WHITE) {
            return WHITE_PAWN_UNIT_MOVEMENT;
        }
        return BLACK_PAWN_UNIT_MOVEMENT;
    }

    @Override
    public final boolean canAttack(UnitMovement movement, int step) {
        if (hasColorOf(Color.WHITE)) {
            return WHITE_PAWN_ATTACK_UNIT_MOVEMENT.contains(movement);
        }
        return BLACK_PAWN_ATTACK_UNIT_MOVEMENT.contains(movement);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
