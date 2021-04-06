package chess.domain;

public enum TeamColor {
    WHITE, BLACK;

    public TeamColor reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public static TeamColor teamColor(String teamColor) {
        if (teamColor.equalsIgnoreCase("WHITE")) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isEnemy(TeamColor teamColor) {
        return teamColor != this;
    }

}
