package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rook extends Piece {

    private static final int SCORE = 5;

    public Rook(Color color) {
        super(SCORE, color);
    }

    @Override
    public boolean isMovablePosition(Position source, Position target, Map<Position, Piece> board) {
        List<List<Integer>> possibleDot = List.of(List.of(0, 1), List.of(1, 0), List.of(0, -1), List.of(-1, 0));
        List<Position> positions = new ArrayList<>(List.of(source, target));
        return isMovableLine(positions, possibleDot, board);
    }
}
