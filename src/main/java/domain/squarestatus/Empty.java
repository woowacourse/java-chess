package domain.squarestatus;

import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;
import domain.type.EmptyType;
import domain.type.Type;

public final class Empty implements SquareStatus {

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
    public Route findRoute(final Position source, final Position target) {
        throw new IllegalStateException(EMPTY_ERROR_MESSAGE);
    }

    @Override
    public boolean isSameColor(final Color color) {
        throw new IllegalStateException(EMPTY_ERROR_MESSAGE);
    }

    @Override
    public boolean isDifferentColor(final Color color) {
        throw new IllegalStateException(EMPTY_ERROR_MESSAGE);
    }

    @Override
    public boolean isSameType(final Type type) {
        return this.emptyType.isSame(type);
    }

    @Override
    public boolean isDifferentType(final Type type) {
        return this.emptyType.isDifferent(type);
    }

}
