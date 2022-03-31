package chess.command;

public abstract class BiCommand<T> extends Command {
    private final T firstArgument;
    private final T secondArgument;

    public BiCommand(Type type, T firstArgument, T secondArgument) {
        super(type);
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }

    public T getFirstArgument() {
        return firstArgument;
    }

    public T getSecondArgument() {
        return secondArgument;
    }
}
