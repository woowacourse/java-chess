package chess.domain.player.type;

public enum TeamColor {
    WHITE("백"),
    BLACK("흑");

    private final String koreanColorName;

    TeamColor(String koreanColorName) {
        this.koreanColorName = koreanColorName;
    }

    public TeamColor opposite() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public String koreanColorName() {
        return koreanColorName;
    }
}
