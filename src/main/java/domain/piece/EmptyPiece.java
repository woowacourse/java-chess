package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.PieceType;
import java.util.List;

public final class EmptyPiece extends Piece {

    private static final String EMPTY_PIECE_CAN_NOT_MOVE_ERROR_MESSAGE = "체스말이 없는 위치 입니다";

    protected EmptyPiece() {
        super(Color.NONE, PieceType.EMPTY);
    }

    @Override
    public List<Location> searchPath(final Location start, final Location end) {
        throw new IllegalArgumentException(EMPTY_PIECE_CAN_NOT_MOVE_ERROR_MESSAGE);
    }
}
