package chess.domain.piece;

import java.util.Arrays;

public enum Side {
    BLACK("black"),
    WHITE("white");

    private final String displayName;

    Side(String displayName) {
        this.displayName = displayName;
    }

    public static Side of(String displayName) {
        return Arrays.stream(values())
                .filter(side -> side.displayName.equals(displayName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 이름에 해당하는 진영이 존재하지 않습니다."));
    }

    public String getDisplayName() {
        return displayName;
    }
}
