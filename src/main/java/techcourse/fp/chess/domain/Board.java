package techcourse.fp.chess.domain;

import java.util.List;
import java.util.Map;

public final class Board {

    public static final int BOARD_SIZE = 8;
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        validate(board);
        this.board = board;
    }

    private void validate(final Map<Position, Piece> board) {
        if (board.size() != Math.pow(BOARD_SIZE, 2)) {
            throw new IllegalArgumentException(String.format("체스판의 사이즈는 %d x %d 여야합니다.", BOARD_SIZE, BOARD_SIZE));
        }
    }

    public boolean move(final Position source, final Position target) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        final List<Position> path = sourcePiece.findPath(source, target, targetPiece.color);
        if (canMoveToTheTarget(path)) {
            board.put(target, sourcePiece);
            board.put(source, new Empty());
            return true;
        }

        return false;
    }

    //TODO: target 빼고 path를 받도록 수정
    private boolean canMoveToTheTarget(final List<Position> path) {
        return path.stream()
                .limit(path.size() - 1)
                .allMatch(position -> board.get(position).isEmpty());
    }
}
