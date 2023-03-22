package chessgame.domain.board;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Camp;
import chessgame.domain.piece.EmptyPiece;
import chessgame.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Coordinate, Piece> board;

    public Board() {
        board = initialize();
    }

    public Map<Coordinate, Piece> initialize() {
        return new HashMap<>(BoardInitialImage.generate());
    }

    public boolean checkCamp(final Coordinate coordinate, final Camp camp) {
        Piece findPiece = board.get(coordinate);
        return findPiece.isSameCamp(camp);
    }

    public void move(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (isMovable(startCoordinate, endCoordinate)) {
            Piece findPiece = board.get(startCoordinate);
            board.put(startCoordinate, new EmptyPiece());
            board.put(endCoordinate, findPiece);
            return;
        }
        throw new IllegalArgumentException("[ERROR] 해당 기물을 옮길 수 없습니다.");
    }

    private boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return isMovableByRule(startCoordinate, endCoordinate) &&
                isNotBlocked(startCoordinate, endCoordinate);
    }

    private boolean isMovableByRule(final Coordinate startCoordinate,
                                    final Coordinate endCoordinate) {
        Piece findPiece = board.get(startCoordinate);
        Piece targetPiece = board.get(endCoordinate);

        if (isPieceExistsAt(endCoordinate)) {
            System.out.println(findPiece.isCatchable(targetPiece.camp(), startCoordinate, endCoordinate));
            return findPiece.isCatchable(targetPiece.camp(), startCoordinate, endCoordinate);
        }
        return findPiece.isMovable(startCoordinate, endCoordinate);
    }

    private boolean isPieceExistsAt(final Coordinate coordinate) {
        Piece findPiece = board.get(coordinate);
        return findPiece.isExist();
    }

    private boolean isNotBlocked(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Piece findPiece = board.get(startCoordinate);

        if (findPiece.canReap()) {
            return true;
        }
        return isNotBlockedWhenNotReap(startCoordinate, endCoordinate);
    }

    private boolean isNotBlockedWhenNotReap(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);
        Coordinate indexCoordinate = directionVector.moveToDirection(startCoordinate);

        System.out.println(indexCoordinate);
        System.out.println(!board.get(indexCoordinate)
                                 .isExist());
        System.out.println(!indexCoordinate.equals(endCoordinate));
        while (!board.get(indexCoordinate)
                     .isExist() && !indexCoordinate.equals(endCoordinate)) {
            indexCoordinate = directionVector.moveToDirection(indexCoordinate);
        }
        return indexCoordinate.equals(endCoordinate);
    }

    public Map<Coordinate, Piece> getBoard() {
        return board;
    }
}
