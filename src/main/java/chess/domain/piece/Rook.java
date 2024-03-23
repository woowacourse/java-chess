package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;

import java.util.List;

public class Rook extends Piece {

    private static final String ERROR_CANNOT_REACH = "룩의 이동 방법으로 갈 수 없는 곳입니다.";
    private static final String ERROR_OBSTACLE_ON_PATH = "이동 경로 중 장애물이 존재합니다.";

    public Rook(PieceColor color, Square square) {
        super(color, square);
    }

    @Override
    public void move(Board board, Square target) {
        validateDirection(target);
        validateObstacle(board, target);
        square = target;
    }

    private void validateDirection(Square target) {
        if (!isSameFileOrRank(target)) {
            throw new IllegalArgumentException(ERROR_CANNOT_REACH);
        }
    }

    private void validateObstacle(Board board, Square target) {
        List<Square> path = square.generatePath(target);
        if (path.stream().anyMatch(board::existOnSquare)) {
            throw new IllegalArgumentException(ERROR_OBSTACLE_ON_PATH);
        }
    }

    private boolean isSameFileOrRank(Square target) {
        return getSquare().isSameFile(target) || getSquare().isSameRank(target);
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
