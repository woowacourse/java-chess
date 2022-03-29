package chess.game;

import static chess.piece.Color.*;

import chess.piece.Color;
import chess.status.State;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Game {

    private State state;
    private Color winColor = NONE;

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
            return state.score().getScore();
        }
        isMoveStatus(inputs);
        isEndStatus();
        return Collections.emptyMap();
    }

    private void isMoveStatus(final List<String> inputs) {
        if (state.canMove()) {
            state.move(MoveCommand.of(inputs.get(1), inputs.get(2)));
        }
    }

    public Board getBoard() {
        return state.getBoard();
    }

    private Color reversColor(final Color color) {
        if (color == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    private void isEndStatus() {
        if (state.isGameEnd()) {
            winColor = reversColor(state.getColor());
            state = state.turn(Command.END);
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

    public Color getWinColor() {
        return winColor;
    }
}
