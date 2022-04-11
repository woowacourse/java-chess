package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import java.util.List;
import java.util.Map;

public final class Pawn extends Piece {
    private static final List<List<Integer>> PAWN_CAPTURE_MOVE = List.of(List.of(1, 1), List.of(-1, 1));
    private static final List<List<Integer>> PAWN_FIRST_MOVE = List.of(List.of(0, 1), List.of(0, 2));
    private static final List<List<Integer>> PAWN_BASIC_MOVE = List.of(List.of(0, 1));
    private static final String FAILED_TO_MOVE_PAWN = "폰 이동에 실패했습니다";
    private static final String ILLEGAL_MOVE_FOR_PAWN = "폰이 이동할 수 있는 지점이 아닙니다";
    private static final Map<Color, Symbol> SYMBOL = Map.of(
            Color.WHITE, Symbol.PAWN_WHITE,
            Color.BLACK, Symbol.PAWN_BLACK);

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public MoveResult movable(Position from, Position to, Board board) {
        if (isCaptureMove(from, to, board)) {
            return MoveResult.SUCCESS;
        }
        validateDistance(from, to, board);
        return MoveResult.SUCCESS;
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
        if (isFirstMove(from)) {
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

    private boolean isFirstMove(Position from) {
        if (this.isBlack()) {
            return from.isSameRank(Rank.SEVEN);
        }
        return from.isSameRank(Rank.TWO);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getScore() {
        return Symbol.getScore(SYMBOL.get(this.color));
    }

    @Override
    public String getName() {
        return Symbol.getName(SYMBOL.get(this.color));
    }
}
