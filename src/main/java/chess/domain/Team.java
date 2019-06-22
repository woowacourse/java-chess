package chess.domain;

import com.sun.javaws.exceptions.InvalidArgumentException;

public enum Team {
    WHITE, BLACK;

    public Team get(int index) {
        if (index != 0 && index != 1) {
            throw new IllegalArgumentException("없는 값 입니다.");
        }
        if (index == 0) {
            return BLACK;
        }
        return WHITE;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
