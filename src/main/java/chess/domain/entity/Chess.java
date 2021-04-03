package chess.domain.entity;

import chess.domain.piece.Color;

import java.util.Objects;
import java.util.UUID;

public class Chess {
    private String id;
    private String name;
    private Color winnerColor;
    private boolean isRunning;

    public Chess(final String name) {
        this(UUID.randomUUID().toString(), name, Color.BLANK, true);
    }

    public Chess(final String id, final String name, final Color winnerColor, final boolean isRunning) {
        this.id = id;
        this.name = name;
        this.winnerColor = winnerColor;
        this.isRunning = isRunning;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Color getWinnerColor() {
        return winnerColor;
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Chess chess = (Chess) o;
        return Objects.equals(id, chess.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
