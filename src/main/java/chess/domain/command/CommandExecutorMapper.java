package chess.domain.command;

import chess.domain.chessGame.ChessGameState;

import java.util.List;

public class CommandExecutorMapper {

    public static final int COMMAND_HEAD_INDEX = 0;
    public static final int CURRENT_POSITION_INDEX = 1;
    public static final int NEXT_POSITION_INDEX = 2;

    private final List<String> inputCommand;
    private final Command command;

    public CommandExecutorMapper(List<String> inputCommand) {
        this.inputCommand = inputCommand;
        while (inputCommand.size() < 3) {
            inputCommand.add(" ");
        }
        command = Command.parseCommand(inputCommand.get(COMMAND_HEAD_INDEX));
    }

    public ChessGameState executeMapped(ChessGameState chessGameState) {
        CommandExecute executor = command.generate(chessGameState);
        return executor.execute(inputCommand.get(CURRENT_POSITION_INDEX), inputCommand.get(NEXT_POSITION_INDEX));
    }
}
