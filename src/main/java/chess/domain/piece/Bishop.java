package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;

import java.util.List;

public class Bishop extends Piece {

    public static final String ERROR_OBSTACLE_ON_PATH = "이동 경로 중 장애물이 존재합니다.";
    private static final String ERROR_CANNOT_REACH = "비숍의 이동 방법으로 갈 수 없는 곳입니다.";

    public Bishop(PieceColor color, Square square) {
        super(color, square);
    }

    @Override
    public void move(Board board, Square target) {
        validateDirection(target);
        validateObstacle(board, target);
        square = target;
    }

    private void validateDirection(Square target) {
        if (!square.isSameDiagonal(target)) {
            throw new IllegalArgumentException(ERROR_CANNOT_REACH);
        }
    }

    private void validateObstacle(Board board, Square target) {
        List<Square> path = square.generatePath(target);
        if (path.stream().anyMatch(board::existOnSquare)) {
            throw new IllegalArgumentException(ERROR_OBSTACLE_ON_PATH);
        }
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
