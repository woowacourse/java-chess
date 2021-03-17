package chess.board;

import chess.piece.Direction;
import chess.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class State {

    private static final List<State> STATE_POOL = new ArrayList<>();

    static {
        for (Piece piece : Piece.values()) {
            iterateTeam(piece);
        }
    }

    private final Piece piece;
    private final Team team;

    private State(Piece piece, Team team) {
        this.piece = piece;
        this.team = team;
    }

    private static void iterateTeam(Piece piece) {
        for (Team team : Team.values()) {
            STATE_POOL.add(new State(piece, team));
        }
    }

    public static State of(Piece piece, Team team) {
        return STATE_POOL.stream()
            .filter(state -> state.piece == piece && state.team == team)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public Direction findMovableDirection(Point source, Point destination) {
        return piece.findMovableDirection(source, destination);
    }

    public int getDirectionLength() {
        return piece.getDirectionLength();
    }

    public boolean isNotSameTeam(State state) {
        return this.team != state.team;
    }

    public boolean isNotEmpty() {
        return team != Team.NONE;
    }

    public boolean isEmpty() {
        return team == Team.NONE;
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

    @Override
    public String toString() {
        return "State{" +
            "piece=" + piece +
            ", team=" + team +
            '}';
    }
}
