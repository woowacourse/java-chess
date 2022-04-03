package chess.domain.piece;

import static chess.domain.piece.PieceProperty.NULL_PIECE;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import java.util.function.Consumer;

public final class NullPiece extends Piece {

    private static final String CANT_MOVE_EMPTY_PIECE = "빈 기물을 움직일 수 없습니다.";

    public NullPiece(final Camp camp) {
        super(camp, NULL_PIECE);
    }

    @Override
    public void move(final Position beforePosition, final Position afterPosition, final Consumer<Piece> movePiece) {
        throw new IllegalArgumentException(CANT_MOVE_EMPTY_PIECE);
    }

    @Override
    public void move(final Positions positions, final Consumer<Piece> movePiece) {
        throw new IllegalArgumentException(CANT_MOVE_EMPTY_PIECE);
    }

    @Override
    public boolean canMove(final Position beforePosition, final Position afterPosition) {
        throw new IllegalStateException(CANT_MOVE_EMPTY_PIECE);
    }

    @Override
    public boolean canMove(final Positions positions) {
        throw new IllegalStateException(CANT_MOVE_EMPTY_PIECE);
    }

    @Override
    public boolean isNullPiece() {
        return true;
    }
}
