package domain.piece.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Direction;
import domain.piece.Piece;

public class Pawn extends Piece {
    private boolean isGoingForward;
    private boolean isFirstMove = true;

    private static final List<Direction> movableDirectionForward = List.of(
            Direction.NORTH
    );

    private static final List<Direction> movableDirectionDiagonal = List.of(
            Direction.NORTH_WEST,
            Direction.NORTH_EAST
    );

    public Pawn(Camp camp) {
        super(camp);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        checkMoveDirection(currentSquare, targetSquare);
        ArrayList<Square> movableCoordinate = getSquares(currentSquare);
        moveForward(currentSquare, movableCoordinate);
        if (movableCoordinate.contains(targetSquare)) {
            isFirstMove = false;
            return movableCoordinate;
        }
        throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
    }

    private void moveForward(Square currentSquare, ArrayList<Square> movableCoordinate) {
        if (isGoingForward && isFirstMove) {
            movableCoordinate.add(fetchTwoStepForwardSquare(currentSquare));
        }
    }

    private ArrayList<Square> getSquares(Square currentSquare) {
        int directionUnit = fetchDirectionUnit();
        int currentFileCoordinate = currentSquare.toCoordinate().get(FILE);
        int currentRankCoordinate = currentSquare.toCoordinate().get(RANK);

        List<Direction> targetDirection = fetchMovableDirections();

        ArrayList<Square> movableSquares = new ArrayList<>();
        addMovableSquares(directionUnit, currentFileCoordinate, currentRankCoordinate, targetDirection, movableSquares);
        return movableSquares;
    }

    private void addMovableSquares(int directionUnit, int currentFileCoordinate, int currentRankCoordinate,
                                   List<Direction> targetDirection, ArrayList<Square> movableCoordinate) {
        for (Direction direction : targetDirection) {
            int fileCoordinate = currentFileCoordinate + directionUnit * direction.getFile();
            int rankCoordinate = currentRankCoordinate + directionUnit * direction.getRank();
            if (isInCoordinateRange(fileCoordinate, rankCoordinate)) {
                continue;
            }
            movableCoordinate.add(new Square(fileCoordinate, rankCoordinate));
        }
    }

    private Square fetchTwoStepForwardSquare(Square currentSquare) {
        int directionUnit = fetchDirectionUnit();
        int currentFileCoordinate = currentSquare.toCoordinate().get(FILE);
        int currentRankCoordinate = currentSquare.toCoordinate().get(RANK);
        return new Square(currentFileCoordinate, currentRankCoordinate + (directionUnit * 2));
    }


    private List<Direction> fetchMovableDirections() {
        if (isGoingForward) {
            return movableDirectionForward;
        }
        return movableDirectionDiagonal;
    }

    private void checkMoveDirection(Square currentSquare, Square targetSquare) {
        int currentFileCoordinate = currentSquare.toCoordinate().get(FILE);
        isGoingForward = currentFileCoordinate == targetSquare.toCoordinate().get(FILE);
    }

    public boolean canMove(Map<Square, Camp> pathInfo, Square targetSquare) {
        Camp targetCamp = pathInfo.get(targetSquare);
        pathInfo.remove(targetSquare);
        boolean existPieceOnPath = isExistPieceOnPath(pathInfo);
        if (isGoingForward) {
            return targetCamp.equals(Camp.NONE) && !existPieceOnPath;
        }
        return isDifferentCamp(targetCamp);
    }

    private int fetchDirectionUnit() {
        if (isWhite()) {
            return 1;
        }
        return -1;
    }
}
