package chess.domain.gamestate.running;

import chess.domain.board.Board;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.gamestate.finished.End;

public class Ready extends Running {

    public Ready(Board board) {
        super(board);
    }

    @Override
    public State processCommand(CommandType command) {
        validateCommand(command);
        if (command == CommandType.START) {
            return new Start(board);
        }
        return new End(board);
    }

    private void validateCommand(CommandType command) {
        if (command != CommandType.START && command != CommandType.END) {
            throw new IllegalArgumentException("[ERROR] start 혹은 end만 가능합니다.");
        }
    }
}
