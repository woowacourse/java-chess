package chess.domain;

@FunctionalInterface
public interface MoveCheck {
    boolean check(int x, int y);
}
