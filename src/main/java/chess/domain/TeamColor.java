package chess.domain;

public enum TeamColor {
    WHITE,
    BLACK;

    public TeamColor reverse() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }

    public boolean isSameColor(TeamColor teamColor) {
        return this.equals(teamColor);
    }

    public boolean isNotSameColor(TeamColor teamColor) {
        return this != teamColor;
    }
}
