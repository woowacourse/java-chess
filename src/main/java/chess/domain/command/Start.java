package chess.domain.command;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.state.State;

import java.util.Map;

public final class Start extends Command {

    private static final String MOVE = "move";
    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private static final int MOVE_COMMAND_SIZE = 3;

    private State state;

    public Start(State state) {
        this.state = state;
    }

    @Override
    public boolean isStart() {
        return true;
    }

    @Override
    public Command execute(String command) {
        String[] commands = command.split(DELIMITER);
        if (commands[COMMAND_INDEX].equals(MOVE) && commands.length == MOVE_COMMAND_SIZE) {
            state = state.movePiece(Position.of(commands[SOURCE_INDEX]), Position.of(commands[DESTINATION_INDEX]));
            return new Start(state);
        }

        if (command.equals(END)) {
            return new End();
        }

        throw new IllegalArgumentException(MOVE + " 혹은 " + END + "를 입력해주세요");
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }
}
