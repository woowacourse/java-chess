package domain.type;

public enum EmptyType implements Type {

    EMPTY;

    public static boolean isEmpty(Type type) {
        return EMPTY == type;
    }
}
