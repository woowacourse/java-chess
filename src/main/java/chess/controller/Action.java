package chess.controller;

@FunctionalInterface
public interface Action {

    void execute(final CommandManagement commandManagement);

}
