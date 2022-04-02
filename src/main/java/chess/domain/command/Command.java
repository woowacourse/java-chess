package chess.domain.command;

import chess.domain.state.State;

public abstract class Command {

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String STATUS = "status";

    public static Command from(final String input) {
        validateInput(input);
        if (input.equals(START)) {
            return new StartCommand();
        }

        if (input.equals(END)) {
            return new EndCommand();
        }

        if (input.startsWith(MOVE)) {
            return new MoveCommand(input);
        }

        return new StatusCommand();
    }

    public abstract State changeChessState(final State state);

    private static void validateInput(final String input) {
        if (!input.equals(START) && !input.equals(END)
                && !input.startsWith(MOVE) && !input.equals(STATUS)) {

            throw new IllegalArgumentException("안내에 따라 올바른 입력을 해주세요.");
        }
    }
}
