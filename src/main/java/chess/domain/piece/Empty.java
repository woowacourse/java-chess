package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.route.Route;

public class Empty implements Placeable {
    private static final String UNSUPPORT_MESSAGE = "위치한 기물이 없습니다.";

    public Empty() {
    }

    @Override
    public Route findRoute(Position fromPosition, Position toPosition) {
        throw new UnsupportedOperationException(UNSUPPORT_MESSAGE);
    }

    @Override
    public boolean isTeam(Team team) {
        throw new UnsupportedOperationException(UNSUPPORT_MESSAGE);
    }

    @Override
    public String getAcronym() {
        throw new UnsupportedOperationException(UNSUPPORT_MESSAGE);
    }

    @Override
    public double getScore() {
        throw new UnsupportedOperationException(UNSUPPORT_MESSAGE);
    }

    @Override
    public Team getTeam() {
        throw new UnsupportedOperationException(UNSUPPORT_MESSAGE);
    }

    @Override
    public PieceType getPieceType() {
        throw new UnsupportedOperationException(UNSUPPORT_MESSAGE);
    }

    @Override
    public boolean isOppositeTeam(Team team) {
        throw new UnsupportedOperationException(UNSUPPORT_MESSAGE);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isNotEmpty() {
        return !isEmpty();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public String toString() {
        return "Empty{}";
    }
}
