package chess.domain.piece.type.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.type.pawn.move.PawnColorMoveStrategy;
import chess.domain.piece.type.pawn.state.InitialPawn;
import chess.domain.piece.type.pawn.state.PawnState;

public class Pawn extends Piece {

    private final PawnColorMoveStrategy pawnColorMoveStrategy;
    private PawnState pawnState;

    public Pawn(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
        // TODO 생성자에서 로직이 들어가는 것이 괜찮을까요? 이정도는 간단하니 괜찮다 생각합니다..
        this.pawnColorMoveStrategy = PawnColorMoveStrategy.byColor(color);
        this.pawnState = new InitialPawn();
    }

    @Override
    public void move(final PiecePosition destination) {
        final Path path = Path.of(piecePosition, destination);
        validatePath(path);
        validateMove(destination);
        piecePosition = destination;
        pawnState = pawnState.next(destination);
    }

    @Override
    protected void validatePath(final Path path) {
        pawnColorMoveStrategy.validateMovementDirection(path);
        pawnState.validatePath(path);
    }

    private void validateMove(final PiecePosition destination) {
        final Path path = Path.of(piecePosition, destination);
        if (!path.isStraight()) {
            throw new IllegalArgumentException("폰은 적이 없는 경우 직선으로만 이동할 수 있습니다.");
        }
    }

    @Override
    public void moveToKill(final Piece enemy) {
        final Path path = Path.of(piecePosition, enemy.piecePosition());
        validatePath(path);
        validateKill(enemy);
        piecePosition = enemy.piecePosition();
        pawnState = pawnState.next(piecePosition);
    }

    @Override
    protected void validateKill(final Piece enemy) {
        super.validateKill(enemy);
        final Path path = Path.of(piecePosition, enemy.piecePosition());
        if (!path.isDiagonal()) {
            throw new IllegalArgumentException("폰은 대각선 위치에 있는 적만 죽일 수 있습니다.");
        }
    }
}
