package domain.piece;

import java.util.Arrays;
import java.util.List;

public enum Camp {
    BLACK,
    WHITE,
    NONE;

    public static final List<Camp> PLAYING_CAMPS = List.of(WHITE, BLACK);

    public static Camp find(String campName) {
        return Arrays.stream(Camp.values())
                .filter(camp -> camp.name().equals(campName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 진영입니다."));
    }

    public Camp fetchOppositeCamp() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }
}
