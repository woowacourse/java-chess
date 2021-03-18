package chess.board;

import chess.piece.Piece;
import chess.piece.Vector;
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

    public Vector findMovableVector(Point source, Point destination) {
        if (team.isBlack()) {
            return piece.findMovableVector(source.opposite(), destination.opposite()).opposite();
        }
        return piece.findMovableVector(source, destination);
    }

    public int getMoveLength() {
        return piece.getMoveLength();
    }

    public boolean isTeam(Team team) {
        return this.team == team;
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

    public boolean isPawn() {
        return this.piece == Piece.PAWN;
    }

    public boolean hasMovableVector(Point source, Point destination) {
        Vector vector = this.findMovableVector(source, destination);

        return vector != null;
    }

    public boolean isKing() {
        return piece == Piece.KING;
    }
}
