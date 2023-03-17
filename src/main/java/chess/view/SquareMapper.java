package chess.view;

import chess.domain.Role;
import chess.domain.Team;
import java.util.EnumMap;
import java.util.Map;

public class SquareMapper {
    private static final Map<Team, Map<Role, String>> mapper = new EnumMap<>(Team.class);

    private SquareMapper() {
    }

    static {
        mapper.put(Team.WHITE, createWhiteTeamRoleMap());
        mapper.put(Team.BLACK, createBlackTeamRoleMap());
        mapper.put(Team.NONE, createNoneTeamRoleMap());
    }

    private static Map<Role, String> createWhiteTeamRoleMap() {
        Map<Role, String> mapper = new EnumMap<>(Role.class);
        mapper.put(Role.PAWN, "p");
        mapper.put(Role.KNIGHT, "n");
        mapper.put(Role.BISHOP, "b");
        mapper.put(Role.ROOK, "r");
        mapper.put(Role.QUEEN, "q");
        mapper.put(Role.KING, "k");
        mapper.put(Role.EMPTY, ".");
        return mapper;
    }

    private static Map<Role, String> createBlackTeamRoleMap() {
        Map<Role, String> mapper = new EnumMap<>(Role.class);
        mapper.put(Role.PAWN, "P");
        mapper.put(Role.KNIGHT, "N");
        mapper.put(Role.BISHOP, "B");
        mapper.put(Role.ROOK, "R");
        mapper.put(Role.QUEEN, "Q");
        mapper.put(Role.KING, "K");
        mapper.put(Role.EMPTY, ".");
        return mapper;
    }

    private static Map<Role, String> createNoneTeamRoleMap() {
        Map<Role, String> mapper = new EnumMap<>(Role.class);
        mapper.put(Role.PAWN, ".");
        mapper.put(Role.KNIGHT, ".");
        mapper.put(Role.BISHOP, ".");
        mapper.put(Role.ROOK, ".");
        mapper.put(Role.QUEEN, ".");
        mapper.put(Role.KING, ".");
        mapper.put(Role.EMPTY, ".");
        return mapper;
    }

    public static String map(Team team, Role role) {
        return mapper.get(team).get(role);
    }
}
