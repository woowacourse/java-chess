package chess.view;

import chess.domain.Role;
import chess.domain.Team;
import java.util.EnumMap;
import java.util.Map;

public class SquareMapper {

    private static final Map<Role, String> mapper = new EnumMap<>(Role.class);

    private SquareMapper() {
    }

    static {
        mapper.put(Role.PAWN, "p");
        mapper.put(Role.KNIGHT, "n");
        mapper.put(Role.BISHOP, "b");
        mapper.put(Role.ROOK, "r");
        mapper.put(Role.QUEEN, "q");
        mapper.put(Role.KING, "k");
        mapper.put(Role.EMPTY, ".");
    }

    public static String map(Team team, Role role) {
        if (!mapper.containsKey(role)) {
            throw new IllegalArgumentException("[ERROR] 일치하는 기물이 없습니다.");
        }
        String value = mapper.get(role);
        return convertAlphabetType(team, value);
    }

    private static String convertAlphabetType(Team team, String value) {
        if (team == Team.BLACK) {
            return value.toUpperCase();
        }
        return value;
    }
}
