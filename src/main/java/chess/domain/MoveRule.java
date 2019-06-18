package chess.domain;

public interface MoveRule {
    boolean check(Position source, Position target);
}
