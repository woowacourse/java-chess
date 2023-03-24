package controller;

@FunctionalInterface
public interface GameExecutor {

    GameExecutor FINISH = none -> {
    };

    void execute(Command command);
}
