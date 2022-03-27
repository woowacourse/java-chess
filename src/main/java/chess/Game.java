package chess;

import chess.piece.Color;
import chess.status.State;
import chess.view.Command;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Game {

    private State state;
    private Color winColor;

    public Game(final State state) {
        this.state = state;
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public Map<Color, Double> run(final String input) {
        final List<String> inputs = Arrays.asList(input.split(" "));
        final Command command = Command.of(inputs.get(0));
        validate(command, inputs);

        state = state.turn(command);

        if (command.isStatus()) {
            return state.getStatus();
        }

        if (state.canMove()) {
            state.move(MoveCommand.of(inputs.get(1) + " " + inputs.get(2)));
        }

        if (state.isGameEnd()) {
            winColor = state.getColor();
            state = state.turn(Command.END);
        }

        return Collections.emptyMap();
    }

    private void validate(final Command command, final List<String> input) {
        if (command.isMove() && input.size() != 3) {
            throw new IllegalArgumentException();
        }

        if (!command.isMove() && input.size() != 1) {
            throw new IllegalArgumentException();
        }
    }

    public Color getWinColor() {
        return winColor;
    }

    public Board getBoard() {
        return state.getBoard();
    }
}
