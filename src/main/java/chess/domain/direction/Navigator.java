package chess.domain.direction;

import chess.domain.direction.core.Square;

import java.util.List;
import java.util.Objects;

public class Navigator {
    private List<Way> wayList;

    public Navigator(List<Way> wayList) {
        this.wayList = wayList;
    }

    public Route getWay(Square source, Square target) {
        try {
            return wayList.stream()
                    .filter(way -> way.canMove(source, target))
                    .map(way -> way.generateRoute(source, target))
                    .findAny()
                    .orElse(null);
        } catch (NullPointerException e){
            throw new IllegalArgumentException("getWay에서 NullPointerException 이 발생");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Navigator)) return false;
        Navigator navigator = (Navigator) o;
        return Objects.equals(wayList, navigator.wayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wayList);
    }
}
