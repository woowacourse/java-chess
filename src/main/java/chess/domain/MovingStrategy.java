package chess.domain;

@FunctionalInterface
public interface MovingStrategy {
    boolean test(Position start, Position end);
}
