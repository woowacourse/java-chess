package chess.domain.piece;

import chess.domain.grid.Grid;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pawn extends Piece {
    public Pawn(boolean isBlack, char x, char y, List<Direction> directions, int stepRange) {
        super(isBlack, x, y, directions, stepRange);
    }

    @Override
    char getName() {
        return 0;
    }
//    private static final char NAME_WHEN_BLACK = 'K';
//    private static final char NAME_WHEN_WHITE = 'k';
//
//    public Pawn(final Boolean isBlack, final char x, final char y) {
//        int a = 1;
//        super(isBlack, x, y, Direction.paw);
//    }
//
//    @Override
//    public List<Position> extractMovablePositions() {
//        if (isBlack()) {
//            List<Position> mergedPosition = new ArrayList<>();
//            mergedPosition.addAll(extractMovablePositionsByOneStep(Direction.blackPawnDirection()));
//            mergedPosition.addAll(extractMovablePositionsByTwoStep(Direction.blackPawnLinearDirection()));
//            return mergedPosition.stream().distinct().collect(Collectors.toList());
//        }
//        List<Position> mergedPosition = new ArrayList<>();
//        mergedPosition.addAll(extractMovablePositionsByOneStep(Direction.whitePawnDirection()));
//        mergedPosition.addAll(extractMovablePositionsByTwoStep(Direction.whitePawnLinearDirection()));
//        return mergedPosition.stream().distinct().collect(Collectors.toList());
//    }
//
//    private List<Position> extractMovablePositionsByOneStep(List<Direction> directions) {
//        return directions
//                .stream()
//                .map(direction -> getPosition().moveBy(direction.getXDegree(), direction.getYDegree()))
//                .filter(position -> !isOutOfRange(position) && !Grid.isOccupied(position))
//                .collect(Collectors.toList());
//    }
//
//    private List<Position> extractMovablePositionsByTwoStep(List<Direction> directions) {
//        return directions
//                .stream()
//                .flatMap(direction -> IntStream
//                        .rangeClosed(1, 2)
//                        .mapToObj(index -> getPosition().moveBy(
//                                direction.getXDegree() * index,
//                                direction.getYDegree() * index
//                        ))
//                        .takeWhile(position -> !isOutOfRange(position) && !Grid.isOccupied(position)))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    char getName() {
//        if (isBlack()) {
//            return NAME_WHEN_BLACK;
//        }
//        return NAME_WHEN_WHITE;
//    }
}