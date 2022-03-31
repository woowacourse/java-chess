package chess.model;

public enum Team {

    BLACK("블랙"),
    WHITE("화이트"),
    NONE("없음"),
    ;

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
