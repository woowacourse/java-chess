package chess.model.piece;

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
}
