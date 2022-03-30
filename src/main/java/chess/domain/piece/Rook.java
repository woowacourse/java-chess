package chess.domain.piece;

import chess.domain.Movement;
import java.util.ArrayList;
import java.util.List;

import chess.domain.Color;
import chess.domain.position.Position;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    protected List<Movement> chooseMovements() {
        return List.of(
                Movement.EAST_UNLIMITED,
                Movement.WEST_UNLIMITED,
                Movement.SOUTH_UNLIMITED,
                Movement.NORTH_UNLIMITED
        );
    }

    @Override
    protected String baseSignature() {
        return "r";
    }

    @Override
    public boolean isCorrectMovement(Position source, Position target, Piece targetPiece) {
        int columnDifference = source.calculateColumnDifferenceTo(target);
        int rowDifference = Math.abs(source.calculateRowDifferenceTo(target));

        return movements.contains(Movement.find(columnDifference, rowDifference));
    }

    @Override
    public List<Position> findRoute(Position source, Position target) {
        List<Position> route = new ArrayList<>();

        int routeLength = source.calculateMaxLinearLengthTo(target);
        int xSlope = source.calculateXSlope(target, routeLength);
        int ySlope = source.calculateYSlope(target, routeLength);

        for (int step = 1; step < routeLength; step++) {
            Position routeNode = source.displacedOf(xSlope * step, ySlope * step);
            route.add(routeNode);
        }
        return route;
    }

    @Override
    public double score() {
        return 5;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
