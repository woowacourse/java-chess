package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.List;

public abstract class Piece {

    protected final Color color;

    protected Piece(final Color color) {
        this.color = color;
    }

    public void validateMovable(Board board, Position start, Position goal) {
        List<Position> root = findRoot(board, start, goal);
        validateMiddlePath(board, root);

        validateSameColorPieceOnGoal(board, goal);
    }

    private void validateSameColorPieceOnGoal(Board board, Position goal) {
        if (board.isSameColorPieceExists(goal, color)) {
            throw new IllegalArgumentException("상대편의 기물만 잡을 수 있습니다.");
        }
    }

    /**
     * 제자리로 움직이는 경우 TODO
     */
    protected abstract List<Position> findRoot(Board board, Position start, Position goal);
    protected abstract void validateMiddlePath(Board board, List<Position> root);

    public boolean isSameColor(Color other) {
        return color == other;
    }

    public boolean isSameColor(Piece other) {
        return color == other.color;
    }

    public boolean isDifferentColor(Color other) {
        return color != other;
    }
}
