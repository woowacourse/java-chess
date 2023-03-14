package chess.view;

import chess.domain.Team;
import chess.domain.Type;
import java.util.Arrays;

public enum SquareMapper {
    KING(Type.KING, "k"),
    QUEEN(Type.QUEEN, "q"),
    ROOK(Type.ROOK, "r"),
    BISHOP(Type.BISHOP,"b"),
    KNIGHT(Type.KNIGHT, "n"),
    PAWN(Type.PAWN, "p");

    private final Type type;
    private final String value;

    SquareMapper(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public static String map(Team team, Type type) {
        return Arrays.stream(values())
                .filter(typeMapper -> typeMapper.type.equals(type))
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
