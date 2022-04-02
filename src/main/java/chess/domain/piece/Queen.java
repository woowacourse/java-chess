package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.view.OutputView;
import java.util.List;

public final class Queen extends Piece {
    private static final List<Integer> QUEEN_ANGLES = List.of(90, 45, 0, -45, -90, -135, 180, 135);
    private static final double SCORE = 9.0;

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean movable(Position from, Position to, Board board) {
        try {
            validateAngle(QUEEN_ANGLES, from, to);   // 1. 각도 확인
            validatePieceOnWay(from, to, board);     // 2. 중간 기물 확인
            validateTarget(board, to);               // 3. 목표 기물 확인
            return true;
        } catch (IllegalStateException exception) {
            OutputView.printError(exception);
            return false;
        }
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
