package chess.model;

public enum Team {

    BLACK("black"),
    WHITE("white"),
    NONE("none"),
    ;

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
