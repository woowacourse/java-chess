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

    public boolean checkCamp(Coordinate coordinate, Camp camp) {
        Piece findPiece = board.get(coordinate);
        return findPiece.isSameCamp(camp);
    }

    public void move(Coordinate startCoordinate, Coordinate endCoordinate) {
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
                !isPieceExistsAt(endCoordinate) &&
                isNotBlocked(startCoordinate, endCoordinate);
    }

    private boolean isMovableByRule(Coordinate startCoordinate, Coordinate endCoordinate) {
        Piece findPiece = board.get(startCoordinate);

        return findPiece.isMovable(startCoordinate, endCoordinate);
    }

    private boolean isPieceExistsAt(Coordinate coordinate) {
        Piece findPiece = board.get(coordinate);

        return findPiece.isExist();
    }

    private boolean isNotBlocked(Coordinate startCoordinate, Coordinate endCoordinate) {
        Piece findPiece = board.get(startCoordinate);

        if (findPiece.canReap()) {
            return true;
        }
        return isNotBlockedWhenNotReap(startCoordinate, endCoordinate);
    }

    private boolean isNotBlockedWhenNotReap(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);
        Coordinate indexCoordinate = directionVector.moveToDirection(startCoordinate);

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
