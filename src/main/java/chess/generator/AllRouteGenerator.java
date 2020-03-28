package chess.generator;

import chess.domain.chesspiece.*;
import chess.domain.move.Direction;
import chess.domain.move.Position;
import chess.domain.move.Route;

import java.util.ArrayList;
import java.util.List;

public class AllRouteGenerator {
    private static final int[][] KNIGHT_DIRECTION = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

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
            positions.add(Position.of(initialPosition.getX() + Direction.UP.getX(), initialPosition.getY()));
            positions.add(Position.of(initialPosition.getX() + Direction.UP.getX() * 2, initialPosition.getY()));
            routes.add(new Route(positions));
        }
    }

    private static void makeRoutes(ChessPiece chessPiece, List<Route> routes, Position initialPosition) {
        List<Direction> directions = chessPiece.getMoveDirections();
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

        for (int index = 0; index < 8; index++) {
            positions = new ArrayList<>();

            addKnightRoute(positions, initialPosition, index);
            routes.add(new Route(positions));
        }
        return routes;
    }

    private static void addKnightRoute(List<Position> positions, Position initialPosition, int index) {
        int x = initialPosition.getX() + KNIGHT_DIRECTION[index][0];
        int y = initialPosition.getY() + KNIGHT_DIRECTION[index][1];

        if (validateCoordinate(x, y)) {
            positions.add(Position.of(x, y));
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
        Position nowPosition = Position.of(x, y);
        positions.add(nowPosition);
        addRouteRecursive(nowPosition, positions, direction);
    }

    private static void addRouteOnce(Position initialPosition, List<Position> positions, Direction direction) {
        int x = initialPosition.getX() + direction.getX();
        int y = initialPosition.getY() + direction.getY();

        if (!validateCoordinate(x, y)) {
            return;
        }
        Position nowPosition = Position.of(x, y);
        positions.add(nowPosition);
    }

    private static boolean validateCoordinate(int x, int y) {
        boolean xInField = (x >= 1 && x <= 8);
        boolean yInField = (y >= 1 && y <= 8);

        return xInField && yInField;
    }
}
