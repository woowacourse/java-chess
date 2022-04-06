package game;

public enum Player {

    BLACK("Black"),
    WHITE("White"),
    NONE("None"),
    ;

    private final String name;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
