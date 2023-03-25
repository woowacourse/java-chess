package chess.domain.database;

public enum Database {

    PRODUCT("chess"),
    TEST("chess_test");

    private final String name;

    Database(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
