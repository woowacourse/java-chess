package chess.game;

import static chess.game.MoveCommand.FROM_POSITION_INDEX;
import static chess.game.MoveCommand.TO_POSITION_INDEX;

import chess.piece.detail.Color;
import chess.status.State;
import chess.view.Command;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Game {

    private static final int MAXIMUM_SPLIT_INPUT_SIZE = 3;
    private static final int MINIMUM_SPLIT_INPUT_SIZE = 1;
    private State state;
    private Color winColor;

    public Game(final State state) {
        this.state = state;
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public Map<Color, Double> run(final String commandInput, final List<String> inputs) {
        final Command command = Command.of(commandInput);
        validate(command, inputs);

        state = state.turn(command);

        return gameProgress(inputs, command);
    }

    private Map<Color, Double> gameProgress(final List<String> inputs, final Command command) {
        if (command.isStatus()) {
            return state.getStatus();
        }

        if (state.canMove()) {
            state.move(MoveCommand.of(inputs.get(FROM_POSITION_INDEX) + " " + inputs.get(TO_POSITION_INDEX)));
        }

        if (state.isGameEnd()) {
            winColor = state.getColor();
            state = state.turn(Command.END);
        }
        return Collections.emptyMap();
    }

    private void validate(final Command command, final List<String> input) {
        if (command.isMove() && input.size() != MAXIMUM_SPLIT_INPUT_SIZE) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 시도해주세요.");
        }

        if (!command.isMove() && input.size() != MINIMUM_SPLIT_INPUT_SIZE) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 시도해주세요.");
        }
    }

    public Color getWinColor() {
        return winColor;
    }

    public Board getBoard() {
        return state.getBoard();
    }
}
