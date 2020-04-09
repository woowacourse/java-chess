package chess.piece;

public enum Team {
    BLACK('B'), WHITE('W'), NONE(' ');

    private final char symbol;

    Team(char symbol) {
        this.symbol = symbol;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isNotSame(Team team) {
        return this != team;
    }

    public Team getOppositeTeam() {
        return isWhite() ? BLACK : WHITE;
    }

    public char getSymbol() {
        return symbol;
    }
}
