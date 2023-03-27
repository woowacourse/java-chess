package domain.board.piece;

import java.util.Arrays;

public enum Camp {
    WHITE,
    BLACK,
    NONE;

    public static Camp findByName(String name) {
        return Arrays.stream(values())
            .filter(camp -> camp.name().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 이름의 진영이 존재하지 않습니다."));
    }
}
