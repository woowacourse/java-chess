package chess.domain.moveStrategy;

import chess.domain.location.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PawnMove implements MoveStrategy {
    private static final List<Integer> COLUMN_DIFFS_TO_KILL = Arrays.asList(-1, 1);
    private final Color color;
    private final int moveUnit;

    public PawnMove(Color color) {
        this.color = color;
        this.moveUnit = color.moveUnit();
    }

    @Override
    public List<Position> movablePositions(Position from, Map<Position, Piece> pieceByPosition) {
        List<Position> positions = positionsToMove(from, pieceByPosition);
        positions.addAll(positionsToKill(from, pieceByPosition));
        return positions;
    }

    private List<Position> positionsToMove(Position from, Map<Position, Piece> pieceByPosition) {
        List<Position> positions = new ArrayList<>();
        if (isEmptyPieceBy(1, from, pieceByPosition)) {
            positions.add(from.move(0, moveUnit));
        }
        if (canMoveDouble(from, pieceByPosition)) {
            positions.add(from.move(0, 2 * moveUnit));
        }
        return positions;
    }

    private boolean isEmptyPieceBy(int count, Position from, Map<Position, Piece> pieceByPosition) {
        if (from.canMove(0, count * moveUnit)) {
            return IntStream.rangeClosed(1, count)
                            .allMatch(number -> pieceByPosition.get(from.move(0, number * moveUnit))
                                                               .isEmpty());
        }
        return false;
    }

    private boolean canMoveDouble(Position from, Map<Position, Piece> pieceByPosition) {
        return from.hasRow(color.initPawnRow()) && isEmptyPieceBy(2, from, pieceByPosition);
    }

    private List<Position> positionsToKill(Position from, Map<Position, Piece> pieceByPosition) {
        return COLUMN_DIFFS_TO_KILL.stream()
                                   .filter(column -> canKill(from, pieceByPosition, column))
                                   .map(column -> from.move(column, moveUnit))
                                   .collect(Collectors.toList());
    }

    private boolean canKill(Position from, Map<Position, Piece> pieceByPosition, Integer column) {
        return from.canMove(column, moveUnit) && pieceByPosition.get(from.move(column, moveUnit))
                                                                .isOpposite(color);
    }
}
