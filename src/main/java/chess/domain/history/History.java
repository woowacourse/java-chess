package chess.domain.history;

import java.util.Objects;

public class History {

    private final String source;
    private final String destination;
    private final String team;

    public History(String source, String destination, String team) {
        this.source = source;
        this.destination = destination;
        this.team = team;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(source, history.source) && Objects.equals(destination, history.destination) && Objects.equals(team, history.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, team);
    }
}
