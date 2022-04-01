package chess;

import chess.domain.postion.Position;
import chess.domain.state.State;
import java.util.List;

public class CommandChecker {

    public static State check(final Command command, final State state) {
        if (command.isStart()) {
            return state.start();
        }

        if (command.isMove()) {
            final List<Position> positions = command.makeSourceTargetPosition();
            return state.changeTurn(positions);
        }

        if (command.isEnd()) {
            return state.end();
        }

        return state;
    }
}
