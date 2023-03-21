package chessgame.domain.board;

import chessgame.domain.piece.Coordinate;
import chessgame.domain.square.Camp;
import chessgame.domain.square.EmptySquare;
import chessgame.domain.square.Square;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Coordinate, Square> board;

    public Board() {
        board = initialize();
    }

    public Map<Coordinate, Square> initialize() {
        return new HashMap<>(BoardInitialImage.generate());
    }

    public boolean checkCamp(Coordinate coordinate, Camp camp) {
        Square findSquare = board.get(coordinate);
        return findSquare.isSameCamp(camp);
    }

    public void move(Coordinate startCoordinate, Coordinate endCoordinate) {
        if (isMovable(startCoordinate, endCoordinate)) {
            Square findSquare = board.get(startCoordinate);

            board.put(startCoordinate, new EmptySquare());
            board.put(endCoordinate, findSquare);
        }
    }

    private boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return isMovableByRule(startCoordinate, endCoordinate) &&
                !isPieceExistsAt(endCoordinate) &&
                isNotBlocked(startCoordinate, endCoordinate);
    }

    private boolean isMovableByRule(Coordinate startCoordinate, Coordinate endCoordinate) {
        Square findSquare = board.get(startCoordinate);

        return findSquare.isMovable(startCoordinate, endCoordinate);
    }

    private boolean isPieceExistsAt(Coordinate coordinate) {
        Square findSquare = board.get(coordinate);

        return findSquare.isExist();
    }

    private boolean isNotBlocked(Coordinate startCoordinate, Coordinate endCoordinate) {
        Square findSquare = board.get(startCoordinate);

        if (findSquare.canReap()) {
            return true;
        }
        return isNotBlockedWhenNotReap(startCoordinate, endCoordinate);
    }

    private boolean isNotBlockedWhenNotReap(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Coordinate directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);
        Coordinate indexCoordinate = startCoordinate.add(directionVector);

        while (!board.get(indexCoordinate)
                     .isExist() && !indexCoordinate.equals(endCoordinate)) {
            indexCoordinate = indexCoordinate.add(directionVector);
        }
        return indexCoordinate.equals(endCoordinate);
    }

    public Map<Coordinate, Square> getBoard() {
        return board;
    }
}
