package chess.domain.piece;

import chess.domain.position.Position;
import chess.utils.BetweenPositionsGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(Type.BISHOP, color);
    }

    @Override
    public boolean isMovableDot(Position source, Position target) {
        return source.gapTwoPositionRow(target) == source.gapTwoPositionColumn(target);
    }

    @Override
    public boolean isMovableLine(Position source, Position target, Map<Position, Piece> board) {
        List<List<Integer>> possibleDot = List.of(List.of(1, 1), List.of(1, -1));
        List<Position> positions = new ArrayList<>(List.of(source, target));
        if (BetweenPositionsGenerator.isPossibleMovePosition(this, positions, possibleDot, board)) {
            return true;
        }
        Collections.reverse(positions);
        return BetweenPositionsGenerator.isPossibleMovePosition(this, positions, possibleDot, board);
    }
}
