package chess.controller.command;

import chess.domain.Result;
import chess.repository.BoardDao;
import chess.view.OutputView;

import java.util.List;


public class StatusCommand extends Command {

    private static final String INVALID_EXECUTE_MESSAGE = "게임이 종료되어 상태를 확인하시려면 status를, 게임을 종료하려면 end를 입력해주세요.";

    private final Result result;

    protected StatusCommand(BoardDao boardDao, OutputView outputView) {
        super(boardDao, CommandType.STATUS, outputView);
        result = boardDao.selectChessGame().calculateResult();
    }

    @Override
    public Command execute(List<String> input) {
        CommandType inputCommandType = CommandType.from(input);
        if (inputCommandType == CommandType.STATUS) {
            return executeStatus();
        }
        if (inputCommandType == CommandType.END) {
            return executeEnd();
        }
        throw new IllegalArgumentException(INVALID_EXECUTE_MESSAGE);
    }

    private Command executeStatus() {
        outputView.printResult(result);
        return this;
    }

    private Command executeEnd() {
        boardDao.deleteAll();
        return new EndCommand(boardDao, outputView);
    }
}
