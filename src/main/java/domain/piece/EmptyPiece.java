package domain.piece;

import domain.Location;
import domain.Section;
import domain.type.Color;
import domain.type.PieceType;
import java.util.List;

public class EmptyPiece extends Piece {

    private static final String EMPTY_PIECE_CAN_NOT_MOVE_ERROR_MESSAGE = "체스말이 없는 위치 입니다";

    private EmptyPiece() {
        super(Color.NONE, PieceType.EMPTY);
    }

    public static EmptyPiece make() {
        return new EmptyPiece();
    }

    @Override
    public List<Location> searchPath(final Section start, final Section end) {
        throw new IllegalArgumentException(EMPTY_PIECE_CAN_NOT_MOVE_ERROR_MESSAGE);
    }

    @Override
    protected boolean isNotMovable(final Section start, final Section end) {
        return true;
    }
}
