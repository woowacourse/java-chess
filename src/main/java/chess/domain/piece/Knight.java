package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.view.OutputView;
import java.util.List;

public final class Knight extends Piece {
    private static final List<List<Integer>> KNIGHT_MOVES = List.of(
            List.of(1, 2), List.of(2, 1),
            List.of(2, -1), List.of(1, -2),
            List.of(-1, -2), List.of(-2, -1),
            List.of(-2, 1), List.of(-1, 2)
    );
    private static final String KNIGHT_CAN_NOT_GET_THERE = "나이트가 이동할 수 없는 지점입니다";

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean movable(Position from, Position to, Board board) {
        try {
            validateDistance(from, to); // 1. dx,dy로 이동 가능한 8점 확인
            validateTarget(board, to);  // 2. 목표 기물 확인
            return true;
        } catch (IllegalStateException exception) {
            OutputView.printError(exception);
            return false;
        }
    }

    private void validateDistance(Position from, Position to) {
        final List<Integer> differential = List.of(from.dx(to), from.dy(to));

        if (!KNIGHT_MOVES.contains(differential)) {
            throw new IllegalStateException(KNIGHT_CAN_NOT_GET_THERE);
        }
    }
}
