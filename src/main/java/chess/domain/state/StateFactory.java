package chess.domain.state;

public class StateFactory {
    public static State init() {
        return new InitialState();
    }
}
