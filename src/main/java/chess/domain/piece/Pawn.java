package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.view.OutputView;
import java.util.List;

public final class Pawn extends Piece {
    private static final List<List<Integer>> PAWN_CAPTURE_MOVE = List.of(List.of(1, 1), List.of(-1, 1));
    private static final List<List<Integer>> PAWN_FIRST_MOVE = List.of(List.of(0, 1), List.of(0, 2));
    private static final List<List<Integer>> PAWN_BASIC_MOVE = List.of(List.of(0, 1));
    private static final String FAILED_TO_MOVE_PAWN = "폰 이동에 실패했습니다";
    private static final String ILLEGAL_MOVE_FOR_PAWN = "폰이 이동할 수 있는 지점이 아닙니다";
    private static final int SCORE = 1;

    private boolean isFirstMove = true;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean movable(Position from, Position to, Board board) {
        try {
            if (isCaptureMove(from, to, board)) {
                isFirstMove = false;
                return true;
            }
            validateDistance(from, to, board);
            isFirstMove = false;
            return true;
        } catch (IllegalStateException exception) {
            OutputView.printError(exception);
            return false;
        }
    }

    // 1. 대각 전진 + 타겟에 상대기물 있으면 바로 true 리턴
    private boolean isCaptureMove(Position from, Position to, Board board) {
        final int dx = from.dx(to);
        final int dy = getDy(from, to);

        return PAWN_CAPTURE_MOVE.contains(List.of(dx, dy)) && board.isEnemyOnTarget(this, to);
    }

    private int getDy(Position from, Position to) {
        int dy = from.dy(to);
        if (this.isBlack()) {
            dy *= -1;
        }
        return dy;
    }

    private void validateDistance(Position from, Position to, Board board) {
        if (isFirstMove) {
            validateFirstMove(from, to, board);
            return;
        }

        validateAfterFirstMove(from, to, board);
    }

    // 2. 첫 수일 땐 1 or 2칸 전진 가능 + 타겟에 기물 없음
    // 3. 첫 수 두 칸일 경우 중간 기물 확인
    private void validateFirstMove(Position from, Position to, Board board) {
        final int dx = from.dx(to);
        final int dy = getDy(from, to);
        if (!PAWN_FIRST_MOVE.contains(List.of(dx, dy))) {
            throw new IllegalStateException(ILLEGAL_MOVE_FOR_PAWN);
        }
        if (dy == 2) {
            validatePieceOnWay(from, to, board);
        }
    }

    // 4. 첫 수 아닐 땐 1칸 전진 가능 + 타겟에 기물 없어야 함
    private void validateAfterFirstMove(Position from, Position to, Board board) {
        final int dx = from.dx(to);
        final int dy = getDy(from, to);

        if (!PAWN_BASIC_MOVE.contains(List.of(dx, dy)) || board.exists(to)) {
            throw new IllegalStateException(FAILED_TO_MOVE_PAWN);
        }
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
