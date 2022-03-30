package chess.domain.piece;

import chess.domain.position.Position;
import chess.utils.BetweenPositionsGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Queen extends Piece {

    public Queen(Color color) {
        super(Type.QUEEN, color);
    }

    @Override
    public boolean isMovableDot(Position source, Position target) {
        return (source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target)) || isSameLine(source, target);
    }

    private boolean isSameLine(Position source, Position target) {
        return source.isSameRow(target) || source.isSameColumn(target);
    }

    @Override
    public boolean isMovableLine(Position source, Position target, Map<Position, Piece> board) {
        if (source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target)) {
            return isPossibleMoveDiagonal(source, target, board);
        }
        return isPossibleMoveStraightLine(source, target, board);
    }

    private boolean isPossibleMoveDiagonal(Position source, Position target, Map<Position, Piece> board) {
        List<List<Integer>> possibleDot = List.of(List.of(1, 1), List.of(1, -1));
        List<Position> positions = new ArrayList<>(List.of(source, target));
        if (BetweenPositionsGenerator.isPossibleMovePosition(this, positions, possibleDot, board)) {
            return true;
        }
        Collections.reverse(positions);
        return BetweenPositionsGenerator.isPossibleMovePosition(this, positions, possibleDot, board);
    }

    private boolean isPossibleMoveStraightLine(Position source, Position target, Map<Position, Piece> board) {
        List<List<Integer>> possibleDot = List.of(List.of(0, 1), List.of(1, 0));
        List<Position> positions = new ArrayList<>(List.of(source, target));
        if (BetweenPositionsGenerator.isPossibleMovePosition(this, positions, possibleDot, board)) {
            return true;
        }
        Collections.reverse(positions);
        return BetweenPositionsGenerator.isPossibleMovePosition(this, positions, possibleDot, board);
    }
}
