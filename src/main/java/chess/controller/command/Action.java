package chess.controller.command;

@FunctionalInterface
public interface Action {

    void execute(final Commands commands);

}
