package chess;

import chess.status.State;
import chess.view.Command;
import java.util.Arrays;
import java.util.List;

public class Game {

    private State state;

    public Game(final State state) {
        this.state = state;
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public void run(final String input) {
        final List<String> inputs = Arrays.asList(input.split(" "));
        final Command command = Command.of(inputs.get(0));
        validate(command, inputs);

        this.state = state.turn(command);
        if (state.canMove()) {
            state.move(MoveCommand.of(inputs.get(1) + " " + inputs.get(2)));
        }
    }

    private void validate(final Command command, final List<String> input) {
        if (command.isMove() && input.size() != 3) {
            throw new IllegalArgumentException();
        }

        if (!command.isMove() && input.size() != 1) {
            throw new IllegalArgumentException();
        }
    }
}
