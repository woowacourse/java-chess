package chess.domain.piece.type.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.strategy.pawn.PawnMoveStrategy;
import chess.domain.piece.type.pawn.state.PawnState;

public class Pawn extends Piece {

    private PawnState pawnState;

    public Pawn(final Color color,
                final PiecePosition piecePosition,
                final PawnMoveStrategy strategy,
                final PawnState pawnState
    ) {
        super(color, piecePosition, strategy);
        this.pawnState = pawnState;
    }

    @Override
    public void move(final PiecePosition destination, final Piece nullablePiece) {
        final Path path = path(destination);
        validatePath(path);

        if (nullablePiece == null) {
            validateMove(destination);
        } else {
            validateKill(nullablePiece);
        }
        piecePosition = destination;
        pawnState = pawnState.next(destination);
    }

    @Override
    protected void validatePath(final Path path) {
        if (!moveStrategy.movable(path)) {
            throw new IllegalArgumentException("폰 움직임 오류");
        }

        pawnState.validatePath(path);
    }

    private void validateMove(final PiecePosition destination) {
        final Path path = path(destination);
        if (!path.isStraight()) {
            throw new IllegalArgumentException("폰은 적이 없는 경우 직선으로만 이동할 수 있습니다.");
        }
    }

    @Override
    protected void validateKill(final Piece enemy) {
        super.validateKill(enemy);
        final Path path = path(enemy.piecePosition());
        if (!path.isDiagonal()) {
            throw new IllegalArgumentException("폰은 대각선 위치에 있는 적만 죽일 수 있습니다.");
        }
    }
}
