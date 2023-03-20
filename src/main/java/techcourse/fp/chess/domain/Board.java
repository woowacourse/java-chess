package techcourse.fp.chess.domain;

import java.util.List;
import java.util.Map;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.UnMovablePiece;

public final class Board {

    private static final int BOARD_SIZE = 8;

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

    public void move(final Position source, final Position target) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        if (sourcePiece.isAlly(targetPiece)) {
            throw new IllegalStateException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }

        final List<Position> path = sourcePiece.findPath(source, target, targetPiece.getColor());

        if (cannotMoveToTheTarget(path)) {
            throw new IllegalArgumentException("이동하려는 경로에 기물이 존재합니다.");
        }

        board.put(target, sourcePiece);
        board.put(source, UnMovablePiece.create());
    }

    private boolean cannotMoveToTheTarget(final List<Position> path) {
        return !path.stream()
                .allMatch(position -> board.get(position).isEmpty());
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
