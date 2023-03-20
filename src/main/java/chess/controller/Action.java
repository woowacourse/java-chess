package chess.controller;

@FunctionalInterface
public interface Action {

    boolean execute(String... args);

}
