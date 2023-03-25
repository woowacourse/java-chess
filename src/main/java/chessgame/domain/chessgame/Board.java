package chessgame.domain.chessgame;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Camp;
import chessgame.domain.piecetype.Empty;
import chessgame.domain.piecetype.PieceType;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Coordinate, PieceType> board;

    public Board() {
        board = initialize();
    }

    public Map<Coordinate, PieceType> initialize() {
        return new HashMap<>(BoardInitialImage.generate());
    }

    public boolean checkCamp(final Coordinate coordinate, final Camp camp) {
        PieceType startPiece = board.get(coordinate);
        return startPiece.isSameCamp(camp);
    }

    public void move(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (isMovable(startCoordinate, endCoordinate)) {
            PieceType startPiece = board.get(startCoordinate);
            board.put(startCoordinate, new Empty());
            board.put(endCoordinate, startPiece);
//            startPiece.checkMoved();
            return;
        }
        throw new IllegalArgumentException("[ERROR] 해당 기물을 옮길 수 없습니다.");
    }

    private boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (!isMovableByRule(startCoordinate, endCoordinate)) {
            System.out.println("룰 안 됨");
        }
        if (!isNotBlocked(startCoordinate, endCoordinate)) {
            System.out.println("장애물");
        }
        return isMovableByRule(startCoordinate, endCoordinate) &&
                isNotBlocked(startCoordinate, endCoordinate);
    }

    private boolean isMovableByRule(final Coordinate startCoordinate,
                                    final Coordinate endCoordinate) {
        PieceType startPiece = board.get(startCoordinate);
        PieceType targetPiece = board.get(endCoordinate);

        if (isPieceEmptyAt(endCoordinate)) {
            return startPiece.isReachableByRule(startCoordinate, endCoordinate);
        }
        return startPiece.isCatchable(targetPiece.camp(), startCoordinate, endCoordinate);
    }

    private boolean isPieceEmptyAt(final Coordinate coordinate) {
        PieceType startPiece = board.get(coordinate);
        return startPiece.isEmpty();
    }

    private boolean isNotBlocked(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        PieceType startPiece = board.get(startCoordinate);

        if (startPiece.canReap()) {
            return true;
        }
        return isNotBlockedWhenNotReap(startCoordinate, endCoordinate);
    }

    private boolean isNotBlockedWhenNotReap(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);
        Coordinate indexCoordinate = directionVector.moveToDirection(startCoordinate);

        while (!board.get(indexCoordinate)
                     .isEmpty() && !indexCoordinate.equals(endCoordinate)) {
            indexCoordinate = directionVector.moveToDirection(indexCoordinate);
        }

        return indexCoordinate.equals(endCoordinate);
    }

    public Map<Coordinate, PieceType> getBoard() {
        return board;
    }
}
