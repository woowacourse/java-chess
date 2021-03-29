package domain.piece.objects;

import domain.piece.position.Direction;
import domain.piece.position.Position;
import domain.score.Score;

import java.util.HashMap;
import java.util.Map;

public class Rook extends Piece {
    private static final Score SCORE = Score.of(5);

    private Rook(String name, boolean isBlack) {
        super(name, SCORE, isBlack);
    }

    public static Rook of(String name, boolean color) {
        return new Rook(name, color);
    }

    public static Map<Position, Rook> initialRookPieces() {
        return new HashMap<Position, Rook>() {{
            put(Position.of("a8"), Rook.of("R", true));
            put(Position.of("h8"), Rook.of("R", true));
            put(Position.of("a1"), Rook.of("r", false));
            put(Position.of("h1"), Rook.of("r", false));
        }};
    }

    @Override
    public boolean canMove(Map<Position, Piece> board, Position start, Position end) {
        if (!isLinear(start, end)) {
            return false;
        }
        Direction direction = getLinearDirection(start, end);
        do {
            start = start.move(direction);
        } while (!start.equals(end) && start.notEmptyPosition() && isEmptyPiecePosition(board, start));

        return start.equals(end) && start.notEmptyPosition()
                && (isEmptyPiecePosition(board, end) || !this.isSameColor(board.get(end)));
    }
}
