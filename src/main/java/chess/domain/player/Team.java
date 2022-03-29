package chess.domain.player;

public enum Team {

    WHITE("화이트"),
    BLACK("블랙");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
