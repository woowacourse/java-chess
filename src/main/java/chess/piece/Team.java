package chess.piece;

public enum Team {
    BLACK("흑"),
    WHITE("백"),
    ;

    private final String title;

    Team(String title) {
        this.title = title;
    }

    public Team inverse() {
        return switch (this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
            default -> throw new IllegalStateException("있을 수 없는 상태입니다.");
        };
    }

    public String getTitle() {
        return title;
    }
}
