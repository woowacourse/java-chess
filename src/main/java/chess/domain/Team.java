package chess.domain;

public enum Team {
    WHITE("white"),
    BLACK("black"),
    BLANK("blank");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
