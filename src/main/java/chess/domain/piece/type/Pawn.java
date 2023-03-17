package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.move.PawnColorMoveStrategy;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

public class Pawn extends Piece {

    private final PawnColorMoveStrategy colorMoveStrategy;
    private boolean isMoved;

    public Pawn(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
        // TODO 질문입니다 !!
        //  생성자에서 로직이 들어가는 것이 괜찮을까요? 이정도는 간단하니 괜찮다 생각합니다..
        colorMoveStrategy = PawnColorMoveStrategy.byColor(color);
        this.isMoved = false;
    }

    @Override
    protected void validateMovable(final Path path) {
        colorMoveStrategy.validateMovementDirection(path);
        if (!isMoved && isPawnSpecialDestination(path)) {
            return;
        }
        if (!path.isUnitDistance()) {
            throw new IllegalArgumentException("폰은 첫 이동이 아닌 이상 1칸 씩만 이동할 수 있습니다.");
        }
    }

    private boolean isPawnSpecialDestination(final Path path) {
        if (Math.abs(path.rankInterval()) != 2) {
            return false;
        }
        return Math.abs(path.fileInterval()) == 0;
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
