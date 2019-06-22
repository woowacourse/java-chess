package chess.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Board {
    private Map<String, Tile> tiles;

    public Board(CreateStrategy strategy) {
        this.tiles = strategy.create();
    }

    // TODO: 2019-06-21 파라미터 이름 변경
    public boolean checkPiecePresentInRoute(List<String> sourceAndTarget) {
        Coordinate sourceCoordinateX = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(0).substring(0, 1)));
        Coordinate sourceCoordinateY = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(0).substring(1)));
        Coordinate targetCoordinateX = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(1).substring(0, 1)));
        Coordinate targetCoordinateY = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(1).substring(1)));

        // TODO: 2019-06-22 vector 어디서 만들지 생각
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));
        Tile tile = tiles.get(sourceAndTarget.get(0));
        Route route = tile.findRouteFromPiece(vector);

        return checkEveryRoute(route);
    }

    private boolean checkEveryRoute(Route route) {
        for (String coordinates : tiles.keySet()) {
            if (route.contains(coordinates) && tiles.get(coordinates).isPiecePresent()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tiles);
    }
}
