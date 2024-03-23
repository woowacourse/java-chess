package chess.piece;

import chess.position.Position;
import chess.position.UnitDirection;
import java.util.Set;

public abstract class Pawn extends Piece {

    private static final Set<UnitDirection> WHITE_PAWN_UNIT_DIRECTION = Set.of(
            UnitDirection.differencesOf(0, 1)
    );
    private static final Set<UnitDirection> WHITE_PAWN_ATTACK_UNIT_DIRECTION = Set.of(
            UnitDirection.differencesOf(1, 1),
            UnitDirection.differencesOf(-1, 1)
    );
    private static final Set<UnitDirection> BLACK_PAWN_UNIT_DIRECTION = Set.of(
            UnitDirection.differencesOf(0, -1)
    );
    private static final Set<UnitDirection> BLACK_PAWN_ATTACK_UNIT_DIRECTION = Set.of(
            UnitDirection.differencesOf(1, -1),
            UnitDirection.differencesOf(-1, -1)
    );

    protected Pawn(Color color) {
        super(color, getPawnDirectionByColor(color));
    }

    private static Set<UnitDirection> getPawnDirectionByColor(Color color) {
        if (color == Color.WHITE) {
            return WHITE_PAWN_UNIT_DIRECTION;
        }
        return BLACK_PAWN_UNIT_DIRECTION;
    }

    @Override
    public final boolean canAttack(Position source, Position destination) {
        UnitDirection direction = source.unitDirectionToward(destination);
        if (hasColorOf(Color.WHITE)) {
            return WHITE_PAWN_ATTACK_UNIT_DIRECTION.contains(direction);
        }
        return BLACK_PAWN_ATTACK_UNIT_DIRECTION.contains(direction);
    }
}
