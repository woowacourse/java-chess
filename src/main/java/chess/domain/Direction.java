package chess.domain;

import chess.domain.position.File;
import chess.domain.position.Rank;

public enum Direction {
    PLUS(1),
    ZERO(0),
    MINUS(-1);

    private final int direction;

    Direction(int direction) {
        this.direction = direction;
    }

    public Rank move(Rank rank, int distance) {
        return rank.move(this.direction * distance);
    }

    public File move(File file) {
        return file.move(this.direction);
    }
}
