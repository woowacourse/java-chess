package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.move.PawnColorMoveStrategy;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.state.InitialPawn;
import chess.domain.piece.state.PawnState;

public class Pawn extends Piece {

    private PawnState pawnState;

    public Pawn(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
        // TODO 질문입니다 !!
        //  생성자에서 로직이 들어가는 것이 괜찮을까요? 이정도는 간단하니 괜찮다 생각합니다..
        this.pawnState = new InitialPawn(PawnColorMoveStrategy.byColor(color));
    }

    @Override
    protected void validateMovable(final Path path) {
        pawnState.validateMovable(path);
    }

    @Override
    public void move(final PiecePosition piecePosition) {
        if (!Path.of(this.piecePosition, piecePosition).isStraight()) {
            throw new IllegalArgumentException("폰은 적이 없는 경우 직선으로만 이동할 수 있습니다.");
        }
        this.piecePosition = piecePosition;
        pawnState = pawnState.next(piecePosition);
    }

    @Override
    public void moveToKill(final Piece enemy) {
        validateKill(enemy);
        this.piecePosition = enemy.piecePosition();
        pawnState = pawnState.next(piecePosition);
    }

    @Override
    protected void validateKill(final Piece enemy) {
        super.validateKill(enemy);
        if (!Path.of(piecePosition, enemy.piecePosition()).isDiagonal()) {
            throw new IllegalArgumentException("폰은 대각선 위치에 있는 적만 죽일 수 있습니다.");
        }
    }
}
