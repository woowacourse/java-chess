package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.point.Point;
import chess.domain.point.Points;

import java.util.Map;

import static chess.domain.Color.*;

public class Chess {
    private final Board board;
    private final Points points;

    private Chess(final Board board, final Points points) {
        this.board = board;
        this.points = points;
    }

    public static Chess create(final Board board, final Points points) {
        return new Chess(board, points);
    }

    public Piece move(final Position source, final Position target, final Color player) {
        final Piece targetPiece = board.move(source, target, player);
        points.decrease(targetPiece);
        return targetPiece;
    }

    public Color findWinner() {
        final Point blackPoint = findPointBy(BLACK);
        final Point whitePoint = findPointBy(WHITE);

        if (whitePoint.isGreaterThan(blackPoint)) {
            return WHITE;
        }
        if (blackPoint.isGreaterThan(whitePoint)) {
            return BLACK;
        }
        return EMPTY;
    }

    public Point findPointBy(final Color color) {
        final int pawnDuplicatedColumnCount = board.countPawnDuplicatedColumn(color);
        return points.calculatePoint(color, pawnDuplicatedColumnCount);
    }

    public Map<Position, Piece> getBoardValue() {
        return board.getBoard();
    }
}
