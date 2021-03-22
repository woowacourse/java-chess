package chess.domain.gamestate.running;

import chess.domain.board.Board;
import chess.domain.gamestate.CommandType;
import chess.domain.gamestate.State;
import chess.domain.gamestate.finished.End;

public class Move extends Running {

    public Move(Board board) {
        super(board);
    }

    @Override
    public State processCommand(CommandType command) {
        validateCommand(command);
        if (command == CommandType.STATUS) {
            return new Status(board);
        }
        if (command == CommandType.MOVE) {
            return this;
        }
        return new End(board);
    }

    public void validateCommand(CommandType command) {
        if (command != CommandType.STATUS && command != CommandType.END && command != CommandType.MOVE) {
            throw new IllegalArgumentException("[ERROR] status, move, end만 가능합니다.");
        }
    }
}
