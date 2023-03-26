package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.WayPoints;

import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

    private static final double SAME_FILE_SCORE = 0.5;
    private static final int DEFAULT_SCORE = 1;

    private boolean isMoved;

    public Pawn(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
        this.isMoved = false;
    }

    @Override
    protected void validateMovable(final Path path) {
        if (!matchDestinationByColor(path)) {
            throw new IllegalArgumentException("흰색 폰은 북쪽으로만, 검정색 폰은 남쪽으로만 이동할 수 있습니다.");
        }
        if (!isMoved && isPawnSpecialDestination(path)) {
            return;
        }
        if (!path.isUnitDistance()) {
            throw new IllegalArgumentException("폰은 첫 이동이 아닌 이상 1칸 씩만 이동할 수 있습니다.");
        }
    }

    private boolean matchDestinationByColor(final Path path) {
        if (color == Color.BLACK) {
            return path.isDestinationRelativelySouth();
        }
        return path.isDestinationRelativelyNorth();
    }

    private boolean isPawnSpecialDestination(final Path path) {
        if (Math.abs(path.rankDistance()) != 2) {
            return false;
        }
        return Math.abs(path.fileDistance()) == 0;
    }

    @Override
    protected WayPoints wayPointsWithCondition(final Path path) {
        if (!isMoved && isPawnSpecialDestination(path)) {
            final List<PiecePosition> wayPoints = path.wayPointsWithDestination();
            return new WayPoints(wayPoints);
        }
        return defaultMove(path);
    }

    private WayPoints defaultMove(final Path path) {
        if (path.isDiagonal()) {
            return new WayPoints(Collections.emptyList());
        }
        return new WayPoints(path.destination());
    }

    public static double calculateScoreByCount(long pawnCount) {
        if (pawnCount > 1) {
            return pawnCount * SAME_FILE_SCORE;
        }
        return DEFAULT_SCORE;
    }

    @Override
    public void move(final PiecePosition piecePosition) {
        if (!new Path(this.piecePosition, piecePosition).isStraight()) {
            throw new IllegalArgumentException("폰은 적이 없는 경우 직선으로만 이동할 수 있습니다.");
        }
        this.piecePosition = piecePosition;
        this.isMoved = true;
    }

    @Override
    public void moveAndKill(final Piece enemy) {
        if (!new Path(piecePosition, enemy.piecePosition()).isDiagonal()) {
            throw new IllegalArgumentException("폰은 대각선 위치에 있는 적만 죽일 수 있습니다.");
        }
        this.piecePosition = enemy.piecePosition();
        this.isMoved = true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double score() {
        return DEFAULT_SCORE;
    }
}
