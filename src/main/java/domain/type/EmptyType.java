package domain.type;

public enum EmptyType implements Type {

    EMPTY;

    public static boolean isEmpty(Type type) {
        return EMPTY == type;
    }

    @Override
    public boolean isSame(final Type type) {
        return this == type;
    }

    @Override
    public boolean isDifferent(final Type type) {
        return this != type;
    }
}
