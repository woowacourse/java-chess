package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

public class Pawn extends Piece {

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
        if (Math.abs(path.rankDisplacement()) != 2) {
            return false;
        }
        return Math.abs(path.fileDisplacement()) == 0;
    }

    @Override
    public void move(final PiecePosition piecePosition) {
        if (!Path.of(this.piecePosition, piecePosition).isStraight()) {
            throw new IllegalArgumentException("폰은 적이 없는 경우 직선으로만 이동할 수 있습니다.");
        }
        this.piecePosition = piecePosition;
        this.isMoved = true;
    }

    @Override
    public void moveToKill(final Piece enemy) {
        validateKill(enemy);
        this.piecePosition = enemy.piecePosition();
        this.isMoved = true;
    }

    @Override
    protected void validateKill(final Piece enemy) {
        super.validateKill(enemy);
        if (!Path.of(piecePosition, enemy.piecePosition()).isDiagonal()) {
            throw new IllegalArgumentException("폰은 대각선 위치에 있는 적만 죽일 수 있습니다.");
        }
    }
}
