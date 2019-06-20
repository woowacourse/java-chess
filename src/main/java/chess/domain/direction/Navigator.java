package chess.domain.direction;

import chess.domain.direction.core.Square;

import java.util.List;

public class Navigator {
    private static final int FIRST_MOVED = 0;

    private List<Way> wayList;

    public Navigator(List<Way> wayList) {
        this.wayList = wayList;
    }

    public Route getWay(Square source, Square target) {
        return wayList.stream()
                .filter(way -> way.isGo(source, target))
                .map(way -> way.generateRoute(source, target))
                .findAny()
                .orElse(null);
    }

    public void firstRemove() {
        wayList.remove(FIRST_MOVED);
    }
}
