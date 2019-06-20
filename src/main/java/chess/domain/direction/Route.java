package chess.domain.direction;


import chess.domain.direction.core.Square;

import java.util.List;
import java.util.Objects;

public class Route {
    private List<Square> squares;
    private boolean isCatch;

    public Route(List<Square> squares) {
        this(squares, true);
    }

    public Route(List<Square> squares, boolean isCatch) {
        this.squares = squares;
        this.isCatch = isCatch;
    }

    int size() {
        return squares.size();
    }

    Square get(int i) {
        return squares.get(i);
    }

    public boolean isCatch() {
        return isCatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(squares, route.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares);
    }
}
