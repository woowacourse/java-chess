package chess.domain.direction;

import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Route {

    List<Position> route;

    private Route(List<Position> route) {
        this.route = route;
    }

    public static Route generateRouteFromOtherPiece(Direction direction, Position source, Position destination) {
        List<Position> route = new ArrayList<>();

        Row row = source.nextRow(direction.getRow());
        Col col = source.nextCol(direction.getCol());

        while (!destination.isSameRow(row) || !destination.isSameColumn(col)) {
            route.add(Position.of(row, col));
            row = row.nextRow(direction.getRow());
            col = col.nextCol(direction.getCol());
        }
        return new Route(route);
    }

    public static Route generateRouteFromKnight(KnightDirection direction, Position source, Position destination) {
        return new Route(Collections.emptyList());
    }

    public static Route generateRouteFromPawn(PawnDirection direction, Position source, Position destination) {
        return new Route(Collections.emptyList());
    }

    public static Route generateRouteFromWhitePawnDoubleMove(PawnDirection direction, Position source, Position destination) {
        List<Position> route = new ArrayList<>();

        Row row = source.nextRow(direction.getRow() - 1);
        Col col = source.nextCol(direction.getCol());

        while (!destination.isSameRow(row) || !destination.isSameColumn(col)) {
            route.add(Position.of(row, col));
            row = row.nextRow(direction.getRow() - 1);
            col = col.nextCol(direction.getCol());
        }
        return new Route(route);
    }

    public static Route generateRouteFromBlackPawnDoubleMove(PawnDirection direction, Position source, Position destination) {
        List<Position> route = new ArrayList<>();

        Row row = source.nextRow(direction.getRow() + 1);
        Col col = source.nextCol(direction.getCol());

        while (!destination.isSameRow(row) || !destination.isSameColumn(col)) {
            route.add(Position.of(row, col));
            row = row.nextRow(direction.getRow() + 1);
            col = col.nextCol(direction.getCol());
        }
        return new Route(route);
    }

    public List<Position> getRoute() {
        return route;
    }
}
