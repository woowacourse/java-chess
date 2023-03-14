package chess.view;

import chess.domain.Role;
import chess.domain.Team;
import java.util.Arrays;

public enum SquareMapper {
    KING(Role.KING, "k"),
    QUEEN(Role.QUEEN, "q"),
    ROOK(Role.ROOK, "r"),
    BISHOP(Role.BISHOP,"b"),
    KNIGHT(Role.KNIGHT, "n"),
    PAWN(Role.PAWN, "p");

    private final Role role;
    private final String value;

    SquareMapper(Role role, String value) {
        this.role = role;
        this.value = value;
    }

    public static String map(Team team, Role role) {
        return Arrays.stream(values())
                .filter(typeMapper -> typeMapper.role.equals(role))
                .map(squareMapper -> convertString(team, squareMapper.value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 타입이 없습니다."));
    }

    private static String convertString(Team team, String value) {
        if (team == Team.BLACK) {
            return value.toUpperCase();
        }
        return value;
    }
}
