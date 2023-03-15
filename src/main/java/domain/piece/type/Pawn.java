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

    // TODO: 2023/03/14 갈 수 있는 칸 범위 검사해줘야됨 
    // TODO: 2023/03/14 위에 거를 메서드로 구현해야됨 
    // TODO: 2023/03/14 마지막에 null 리턴 아니고 에러 던져야 됨
    @Override
    public List<Square> fetchMovePath(Square currentSquare, Square targetSquare) {
        List<Direction> targetDirection = movableDirectionDiagonal;
        isGoingForward = currentSquare.toCoordinate().get(FILE).equals(targetSquare.toCoordinate().get(FILE));
        if (isGoingForward) {
            targetDirection = movableDirectionForward;
        }

        ArrayList<Square> movableCoordinate = new ArrayList<>();
        int directionUnit = fetchDirectionUnit();

        int currentFileCoordinate = currentSquare.toCoordinate().get(FILE);
        int currentRankCoordinate = currentSquare.toCoordinate().get(RANK);

        for (Direction direction : targetDirection) {
            int fileCoordinate = currentFileCoordinate + directionUnit * direction.getFile();
            int rankCoordinate = currentRankCoordinate + directionUnit * direction.getRank();
            if (isInCoordinateRange(fileCoordinate, rankCoordinate)) {
                continue;
            }
            movableCoordinate.add(new Square(fileCoordinate, rankCoordinate));
        }

        if (targetDirection.equals(movableDirectionForward)) {
            if (isFirstMove) {
                movableCoordinate.add(new Square(currentFileCoordinate, currentRankCoordinate + (directionUnit * 2)));
            }
        }

        if (movableCoordinate.contains(targetSquare)) {
            isFirstMove = false;
            return movableCoordinate;
        }
        return null;
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
