package chess;

import java.util.Objects;

public class State {

    private final Piece piece;
    private final Team team;

    public State(Piece piece, Team team) {
        this.piece = piece;
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state = (State) o;
        return Objects.equals(piece, state.piece) && team == state.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, team);
    }
}
