package chess;

import java.util.Objects;

public class User {
    private String id;
    private int gameId;

    public User(String id, int gameId) {
        this.id = id;
        this.gameId = gameId;
    }

    public String getUserId() {
        return id;
    }

    public int getGameId() {
        return gameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return gameId == user.gameId &&
                Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameId);
    }
}
