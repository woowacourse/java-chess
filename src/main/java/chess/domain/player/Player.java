package chess.domain.player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player {
    private int id;
    private String username;
    private String password;
    private Map<Result, Integer> record = new HashMap<>();

    public Player(final String username, final String password) {
        this.username = username;
        this.password = password;
        record.put(Result.WIN, 0);
        record.put(Result.LOSE, 0);
        record.put(Result.DRAW, 0);
    }

    public void finishAgainst(Player other, Result result) {
        record.put(result, record.get(result) + 1);
        other.record.put(result.getOpposite(), other.record.get(result.getOpposite()) + 1);
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int recordOf(Result result) {
        return record.get(result);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Player player = (Player)o;
        return id == player.id &&
            Objects.equals(username, player.username) &&
            Objects.equals(password, player.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
