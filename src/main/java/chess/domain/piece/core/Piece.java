package chess.domain.piece.core;

import chess.domain.direction.Navigator;
import chess.domain.direction.Route;
import chess.domain.direction.core.Square;

import java.util.Objects;

public abstract class Piece {
    Team team;
    Type type;
    protected Navigator navigator;

    public Piece(Team team, Type type) {
        this(team, type, null);
    }

    public Piece(Team team, Type type, Navigator navigator) {
        this.team = team;
        this.type = type;
        this.navigator = navigator;
    }

    public Route getRoute(Square source, Square target) {
        Route route = navigator.getWay(source, target);
        if (route == null) {
            throw new IllegalArgumentException("갈 수 없습니다.");
        }
        return route;
    }

    public Piece move() {
        return this;
    }

    public boolean isTeam(Piece piece) {
        return team.equals(piece.team);
    }

    public boolean isTeam(Team team) {
        return this.team.equals(team);
    }

    public boolean isType(Type type) {
        return this.type.equals(type);
    }

    public Team getTeam() {
        return team;
    }

    public Type getType(){
        return type;
    }

    public double getScore() {
        return type.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece)) return false;
        Piece piece = (Piece) o;
        return getTeam() == piece.getTeam() &&
                getType() == piece.getType() &&
                Objects.equals(navigator, piece.navigator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeam(), getType(), navigator);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "team=" + team +
                ", type=" + type +
                ", navigator=" + navigator +
                '}';
    }
}
