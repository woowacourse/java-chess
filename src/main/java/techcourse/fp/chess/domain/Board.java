package techcourse.fp.chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Empty;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.PieceType;
import techcourse.fp.chess.domain.piece.Turn;

public final class Board {

    private static final int BOARD_SIZE = 8;
    private static final int NUMBER_OF_KING = 2;

    private final Map<Position, Piece> board;
    private final Turn turn;

    public Board(final Map<Position, Piece> board, final Turn turn) {
        validate(board);
        this.board = new HashMap<>(board);
        this.turn = turn;
    }

    private void validate(final Map<Position, Piece> board) {
        if (board.size() != Math.pow(BOARD_SIZE, 2)) {
            throw new IllegalArgumentException(String.format("체스판의 사이즈는 %d x %d 여야합니다.", BOARD_SIZE, BOARD_SIZE));
        }
    }

    public void move(final Position source, final Position target) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        validateTurn(sourcePiece);
        final List<Position> path = sourcePiece.findPath(source, target, targetPiece);
        validateObstacle(path);

        board.put(target, sourcePiece);
        board.put(source, Empty.create());

        turn.nextTurn();
    }

    public boolean isGameEnd() {
        return board.values()
                .stream()
                .filter(piece -> piece.isSamePieceType(PieceType.KING))
                .count() < NUMBER_OF_KING;
    }

    public Color findWinner() {
        if (!isGameEnd()) {
            throw new IllegalStateException("아직 게임이 끝나지 않았습니다.");
        }
        return turn.getPreviousTurn();
    }

    private void validateTurn(final Piece sourcePiece) {
        if (turn.isOtherSide(sourcePiece.getColor())) {
            throw new IllegalArgumentException("상대방의 기물을 움직일 수 없습니다.");
        }
    }

    private void validateObstacle(final List<Position> path) {
        if (hasObstacle(path)) {
            throw new IllegalArgumentException("이동하려는 경로에 기물이 존재합니다.");
        }
    }

    private boolean hasObstacle(final List<Position> path) {
        return !path.stream()
                .allMatch(position -> board.get(position).isEmpty());
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
