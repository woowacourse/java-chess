package chess.controller.command;

import chess.repository.BoardDao;
import chess.view.OutputView;

import java.util.List;

import static chess.controller.command.CommandType.INVALID_COMMAND_MESSAGE;

public class ReadyCommand extends Command {

    protected ReadyCommand(BoardDao boardDao, OutputView outputView) {
        super(boardDao, CommandType.READY, outputView);
    }

    @Override
    public Command execute(List<String> input) {
        CommandType inputCommandType = CommandType.from(input);
        if (inputCommandType != CommandType.START) {
            throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
        }
        return new StartCommand(boardDao);
    }
}
