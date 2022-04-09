package chess.model.piece;

import java.util.Arrays;
import java.util.List;

public enum Color {

    WHITE,
    BLACK,
    EMPTY
    ;

    public static List<Color> getPlayerColors() {
        return List.of(WHITE, BLACK);
    }

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public static Color findColor(String name) {
        return Arrays.stream(values())
                .filter(color -> color.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알맞은 팀이 아닙니다: " + name));
    }
}
