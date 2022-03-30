package chess.domain.piece;

import chess.domain.position.Position;
import chess.utils.PossibleMoveLinePositionChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Rook extends Piece {

    public Rook(Color color) {
        super(Type.ROOK, color);
    }

    @Override
    public boolean isMovableDot(Position source, Position target) {
        return source.isSameRow(target) || source.isSameColumn(target);
    }

    @Override
    public boolean isMovableLine(Position source, Position target, Map<Position, Piece> board) {
        List<List<Integer>> possibleDot = List.of(List.of(0, 1), List.of(1, 0));
        List<Position> positions = new ArrayList<>(List.of(source, target));
        if (PossibleMoveLinePositionChecker.isPossibleMovePosition(positions, possibleDot, board)) {
            return true;
        }
        Collections.reverse(positions);
        return PossibleMoveLinePositionChecker.isPossibleMovePosition(positions, possibleDot, board);
    }

    @Override
    public boolean isDotPiece() {
        return false;
    }
}
