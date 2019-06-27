package chess.model.piece;

import chess.model.board.Coordinate;
import chess.model.vector.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Empty implements Piece {

    private String team;

    @Override
    public Route produceRoute(List<Coordinate> coordinates, Vector vector) {
        return new Route(new ArrayList<>());
    }

    @Override
    public String askTeamColor() {
        return null;
    }

    @Override
    public Piece cloneSelf() {
        return null;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public double getScore() {
        return 0;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empty empty = (Empty) o;
        return Objects.equals(team, empty.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
