package domain.chessboard;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;

public class Empty implements SquareStatus {

    private static final String EMPTY_ERROR_MESSAGE = "기물이 없습니다.";

    private final EmptyType emptyType;

    public Empty(final EmptyType emptyType) {
        this.emptyType = emptyType;
    }

    @Override
    public Type getType() {
        return emptyType;
    }

    @Override
    public Color getColor() {
        throw new IllegalStateException(EMPTY_ERROR_MESSAGE);
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
