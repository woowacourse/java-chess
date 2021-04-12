package chess.dao;

public final class ConnectionProperty {
    private final String server;
    private final String database;
    private final String option;
    private final String userName;
    private final String password;

    public ConnectionProperty() {
        this("localhost:13306", "db_name", "?useSSL=false&serverTimezone=UTC", "root", "root");
    }

    public ConnectionProperty(final String server, final String database, final String option, final String userName,
                              final String password) {
        this.server = server;
        this.database = database;
        this.option = option;
        this.userName = userName;
        this.password = password;
    }

    public final String getServer() {
        return server;
    }

    public final String getDatabase() {
        return database;
    }

    public final String getOption() {
        return option;
    }

    public final String getUserName() {
        return userName;
    }

    public final String getPassword() {
        return password;
    }
}
