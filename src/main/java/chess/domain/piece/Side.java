package chess.domain.piece;

public enum Side {
    BLACK("black"),
    WHITE("white");

    private final String displayName;

    Side(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
