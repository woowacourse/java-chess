package chess.generator;

import chess.domain.chesspiece.*;
import chess.domain.move.Coordinate;
import chess.domain.move.Direction;
import chess.domain.move.Position;
import chess.domain.move.Route;

import java.util.ArrayList;
import java.util.List;

public class AllRouteGenerator {
    private static final int[][] KNIGHT_DIRECTION = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
    private static final int KNIGHT_DIRECTION_MIN = 0;
    private static final int KNIGHT_DIRECTION_MAX = 8;
    private static final int FIELD_MAX_SIZE = 8;
    private static final int FIELD_MIN_SIZE = 1;

    private AllRouteGenerator() {
    }

    public static List<Route> getAllRoute(ChessPiece chessPiece, Position position) {
        List<Route> routes = new ArrayList<>();

        if (chessPiece instanceof Knight) {
            return makeKnightRoute(position);
        }
        makeInitialPawnRoutes(chessPiece, routes, position);
        makeRoutes(chessPiece, routes, position);
        return routes;
    }

    private static void makeInitialPawnRoutes(ChessPiece chessPiece, List<Route> routes, Position initialPosition) {
        List<Position> positions = new ArrayList<>();
        boolean isPawn = chessPiece instanceof Pawn;

        if (isPawn && ((Pawn) chessPiece).isFirstMove()) {
            Coordinate pawnInitialX = Coordinate.of(initialPosition.getX() + Direction.UP.getX());
            Coordinate pawnDoubleInitialX = Coordinate.of(initialPosition.getX() + Direction.UP.getX() * 2);
            Coordinate pawnInitialY = Coordinate.of(initialPosition.getY());

            positions.add(Position.of(pawnInitialX, pawnInitialY));
            positions.add(Position.of(pawnDoubleInitialX, pawnInitialY));
            routes.add(new Route(positions));
        }
    }

    private static void makeRoutes(ChessPiece chessPiece, List<Route> routes, Position initialPosition) {
        Direction[] directions = chessPiece.getMoveDirections();
        List<Position> positions;
        for (Direction direction : directions) {
            positions = new ArrayList<>();

            makeRouteByDirection(chessPiece, positions, direction, initialPosition);
            routes.add(new Route(positions));
        }
    }

    private static List<Route> makeKnightRoute(Position initialPosition) {
        List<Position> positions;
        List<Route> routes = new ArrayList<>();

        for (int i = KNIGHT_DIRECTION_MIN; i < KNIGHT_DIRECTION_MAX; i++) {
            positions = new ArrayList<>();

            addKnightRoute(positions, initialPosition, i);
            routes.add(new Route(positions));
        }
        return routes;
    }

    private static void addKnightRoute(List<Position> positions, Position initialPosition, int index) {
        int x = initialPosition.getX() + KNIGHT_DIRECTION[index][0];
        int y = initialPosition.getY() + KNIGHT_DIRECTION[index][1];

        if (validateCoordinate(x, y)) {
            positions.add(Position.of(Coordinate.of(x), Coordinate.of(y)));
        }
    }

    private static void makeRouteByDirection(ChessPiece chessPiece, List<Position> positions, Direction direction, Position initialPosition) {
        if (haveToRecursiveAddition(chessPiece)) {
            addRouteRecursive(initialPosition, positions, direction);
            return;
        }
        addRouteOnce(initialPosition, positions, direction);
    }

    private static boolean haveToRecursiveAddition(ChessPiece chessPiece) {
        boolean isBishop = chessPiece instanceof Bishop;
        boolean isQueen = chessPiece instanceof Queen;
        boolean isRook = chessPiece instanceof Rook;

        return isBishop || isQueen || isRook;
    }

    private static void addRouteRecursive(Position initialPosition, List<Position> positions, Direction direction) {
        int x = initialPosition.getX() + direction.getX();
        int y = initialPosition.getY() + direction.getY();

        if (!validateCoordinate(x, y)) {
            return;
        }
        Position nowPosition = Position.of(Coordinate.of(x), Coordinate.of(y));
        positions.add(nowPosition);
        addRouteRecursive(nowPosition, positions, direction);
    }

    private static void addRouteOnce(Position initialPosition, List<Position> positions, Direction direction) {
        int x = initialPosition.getX() + direction.getX();
        int y = initialPosition.getY() + direction.getY();

        if (!validateCoordinate(x, y)) {
            return;
        }
        Position nowPosition = Position.of(Coordinate.of(x), Coordinate.of(y));
        positions.add(nowPosition);
    }

    private static boolean validateCoordinate(int x, int y) {
        boolean xInField = (x >= FIELD_MIN_SIZE && x <= FIELD_MAX_SIZE);
        boolean yInField = (y >= FIELD_MIN_SIZE && y <= FIELD_MAX_SIZE);

        return xInField && yInField;
    }
}
