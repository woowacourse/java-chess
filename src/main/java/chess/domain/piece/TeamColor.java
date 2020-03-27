package chess.domain.piece;

import java.util.Objects;

public enum TeamColor {
    WHITE,
    BLACK,
    NONE;

    public String getPieceName(String name) {
        validateNull(name);
        if (this == BLACK) {
            return name.toUpperCase();
        }
        if (this == WHITE) {
            return name.toLowerCase();
        }
        return name;
    }

    private void validateNull(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("null이 들어왔습니다.");
        }
    }
}
