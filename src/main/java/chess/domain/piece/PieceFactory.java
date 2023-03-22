package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Team;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class PieceFactory {
    private static final Map<Role, Function<Team, Piece>> FACTORY = new EnumMap<>(Role.class);

    private PieceFactory() {}

    static {
        FACTORY.put(Role.PAWN, Pawn::of);
        FACTORY.put(Role.ROOK, Rook::of);
        FACTORY.put(Role.KNIGHT, Knight::of);
        FACTORY.put(Role.BISHOP, Bishop::of);
        FACTORY.put(Role.QUEEN, Queen::of);
        FACTORY.put(Role.KING, King::of);
        FACTORY.put(Role.EMPTY, ignore -> Empty.INSTANCE);
    }

    public static Piece of(Role role, Team team) {
        return FACTORY.get(role).apply(team);
    }
}
