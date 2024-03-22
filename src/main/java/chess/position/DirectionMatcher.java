package chess.position;

public interface DirectionMatcher {

    boolean matches(Position source, Position destination);
}
