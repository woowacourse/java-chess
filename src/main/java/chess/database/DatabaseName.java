package chess.database;

public enum DatabaseName {

    PRODUCT("chess"),
    TEST("chess_test");

    private final String name;

    DatabaseName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
