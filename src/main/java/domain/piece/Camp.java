package domain.piece;

import java.util.List;

public enum Camp {
    BLACK,
    WHITE,
    NONE;

    public static final List<Camp> PLAYING_CAMPS = List.of(WHITE, BLACK);

    public Camp fetchOppositeCamp() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }
}
