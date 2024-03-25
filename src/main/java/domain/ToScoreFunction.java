package domain;

@FunctionalInterface
public interface ToScoreFunction<T> {
    Score applyAsDouble(T value);
}
