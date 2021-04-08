package chess.dao;

public enum TurnState {
    WHITE("white"),
    BLACK("black"),
    NOT_STARTED("not started");

    private String stringFormat;

    TurnState(final String stringFormat) {
        this.stringFormat = stringFormat;
    }

    public String stringFormat() {
        return this.stringFormat;
    }
}
