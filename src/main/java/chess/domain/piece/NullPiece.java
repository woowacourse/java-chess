package chess.domain.piece;

import static chess.domain.piece.PieceProperty.NULL_PIECE;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.board.UnitDirectVector;
import java.util.Collections;
import java.util.List;
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
    public boolean checkCanMoveByDistance(final Position beforePosition, final Position afterPosition) {
        throw new IllegalStateException(CANT_MOVE_EMPTY_PIECE);
    }

    @Override
    public boolean checkCanMoveByDistance(final Positions positions) {
        throw new IllegalStateException(CANT_MOVE_EMPTY_PIECE);
    }

    @Override
    public boolean isNullPiece() {
        return true;
    }

    @Override
    public List<UnitDirectVector> getPossibleDirections() {
        return Collections.emptyList();
    }
}
