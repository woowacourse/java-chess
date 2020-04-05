package chess.domain.player;

import java.util.Objects;

public class Player {
    private int id;
    private String username;
    private String password;
    private Record record;

    public Player(final String username, final String password) {
        this.username = username;
        this.password = password;
        this.record = new Record();
    }

    public Player(final String username, final String password, final Record record) {
        this.username = username;
        this.password = password;
        this.record = record;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void finishAgainst(Player other, Result result) {
        record.add(result);
        other.record.add(result.getOpposite());
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

    public void setRecord(final Record record) {
        this.record = record;
    }
}
