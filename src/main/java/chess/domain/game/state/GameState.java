package chess.domain.game.state;

public interface GameState {


    GameState start();

    GameState end();

    GameState run();

    StatusType getStatusType();

    default String getStateName() {
        return getStatusType().getStatusName();
    }

    boolean isStarted();

    default boolean notStarted() {
        return !isStarted();
    }
}
