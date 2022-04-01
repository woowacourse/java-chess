package chess.game;

import static chess.piece.Color.NONE;

import chess.dto.CommandRequest;
import chess.piece.Color;
import chess.status.State;
import java.util.Collections;
import java.util.Map;

public class Game {

    private State state;
    private Color winColor = NONE;

    public Game(final State state) {
        this.state = state;
    }

    public Map<Color, Double> run(final CommandRequest commandRequest) {
        final Command command = commandRequest.getCommandType();
        state = state.turn(command);
        isMoveStatus(commandRequest.getFrom(), commandRequest.getTo());
        isEndStatus();
        return score(command);
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    private Map<Color, Double> score(final Command command) {
        if (command.isStatus()) {
            return state.score().getScore();
        }
        return Collections.emptyMap();
    }

    private void isMoveStatus(final Position from, final Position to) {
        if (state.canMove()) {
            state.move(new MoveCommand(from, to));
        }
    }

    public Board getBoard() {
        return state.getBoard();
    }

    private Color reversColor(final Color color) {
        return color.reverse();
    }

    private void isEndStatus() {
        if (state.isGameEnd()) {
            winColor = reversColor(state.getColor());
            state = state.turn(Command.END);
        }
    }

    public Color getWinColor() {
        return winColor;
    }
}
