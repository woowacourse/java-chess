package chess.model;

public enum Team {

    BLACK("BLACK"),
    WHITE("WHITE"),
    NONE("NONE"),
    ;

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
