package domain.piece.type;

import java.util.ArrayList;
import java.util.List;

import domain.board.Square;
import domain.piece.Camp;
import domain.piece.Direction;
import domain.piece.Piece;

public class Pawn extends Piece {
    private boolean isFirstMove = true;
    private static final List<List<Integer>> movableDirection = List.of(
            List.of(0, 1),
            List.of(-1, 1),
            List.of(1, 1)
    );

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
    public List<Square> fetchMovableCoordinate(Square currentSquare, Square targetSquare) {
        List<Direction> targetDirection = movableDirectionDiagonal;
        if (currentSquare.toCoordinate().get(FILE).equals(targetSquare.toCoordinate().get(FILE))) {
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


    private int fetchDirectionUnit() {
        if (isWhite()) {
            return 1;
        }
        return -1;
    }
}
