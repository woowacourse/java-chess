package domain.chessboard;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;

public class Empty implements SquareStatus {

    private static final String EMPTY_ERROR_MESSAGE = "기물이 없습니다.";

    private final EmptyType emptyType;
    private final Color color;

    public Empty() {
        this.emptyType = EmptyType.EMPTY;
        this.color = Color.NONE;
    }

    @Override
    public Type getType() {
        return emptyType;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public double getScore() {
        return 0;
    }

    @Override
    public Route findRoute(MovePosition movePosition) {
        throw new IllegalStateException(EMPTY_ERROR_MESSAGE);
    }

}
