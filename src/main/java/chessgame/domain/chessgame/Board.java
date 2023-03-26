package chessgame.domain.chessgame;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Empty;
import chessgame.domain.piece.Piece;
import chessgame.domain.piece.PieceType;

import java.util.Map;

public class Board {

    private final Map<Coordinate, Piece> board;

    public Board(Map<Coordinate, Piece> board) {
        this.board = board;
    }

    public boolean checkCamp(final Coordinate coordinate, final Camp camp) {
        Piece startPiece = board.get(coordinate);
        return startPiece.isSameCamp(camp);
    }

    public boolean move(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        if (isMovable(startCoordinate, endCoordinate)) {
            Piece startPiece = board.get(startCoordinate);
            Piece endPiece = board.get(endCoordinate);
            board.put(startCoordinate, new Empty());
            board.put(endCoordinate, startPiece);
            startPiece.checkMoved();
            return endPiece.isSameTypeWith(PieceType.KING);
        }
        throw new IllegalArgumentException("[ERROR] 해당 기물을 옮길 수 없습니다.");
    }

    private boolean isMovable(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        return isMovableByRule(startCoordinate, endCoordinate) &&
                isNotBlocked(startCoordinate, endCoordinate);
    }

    private boolean isMovableByRule(final Coordinate startCoordinate,
                                    final Coordinate endCoordinate) {
        Piece startPiece = board.get(startCoordinate);
        Piece targetPiece = board.get(endCoordinate);

        if (isPieceEmptyAt(endCoordinate)) {
            return startPiece.isReachableByRule(startCoordinate, endCoordinate);
        }
        return startPiece.isCatchable(targetPiece.camp(), startCoordinate, endCoordinate);
    }

    private boolean isPieceEmptyAt(final Coordinate coordinate) {
        Piece startPiece = board.get(coordinate);
        return startPiece.isEmpty();
    }

    private boolean isNotBlocked(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        Piece startPiece = board.get(startCoordinate);

        if (startPiece.canReap()) {
            return true;
        }
        return isNotBlockedWhenNotReap(startCoordinate, endCoordinate);
    }

    private boolean isNotBlockedWhenNotReap(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        DirectionVector directionVector = DirectionVector.calculate(startCoordinate, endCoordinate);
        Coordinate indexCoordinate = directionVector.moveToDirection(startCoordinate);

        while (board.get(indexCoordinate).isEmpty() && !indexCoordinate.equals(endCoordinate)) {
            indexCoordinate = directionVector.moveToDirection(indexCoordinate);
        }
        return indexCoordinate.equals(endCoordinate);
    }

    public Map<Coordinate, Piece> getBoard() {
        return board;
    }
}
