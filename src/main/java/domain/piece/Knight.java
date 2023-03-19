package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.PieceType;
import domain.type.direction.PieceMoveDirection;
import java.util.List;
import view.PieceView;

public final class Knight extends Piece {

    private Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    public static Knight makeBlack() {
        return new Knight(Color.BLACK);
    }

    public static Knight makeWhite() {
        return new Knight(Color.WHITE);
    }

    @Override
    public List<Location> searchPath(final Location start, final Location end) {
        if (isNotMovable(start, end)) {
            throw new IllegalArgumentException(PieceView.findSign(this) + IMPOSSIBLE_MOVE_ERROR_MESSAGE);
        }
        return List.of(end);
    }

    @Override
    protected boolean isNotMovable(final Location start, final Location end) {
        PieceMoveDirection direction = PieceMoveDirection.find(start, end);
        return direction.isEightCardinalDirection();
    }
}
