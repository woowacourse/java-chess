package chess.controller.command;

import chess.domain.ChessGame;
import chess.view.OutputView;

import java.util.List;

public class EndCommand extends Command {

    private static final String INVALID_EXECUTE_MESSAGE = "게임이 종료되었습니다.";

    protected EndCommand(ChessGame chessGame, OutputView outputView) {
        super(chessGame, CommandType.END, outputView);
    }

    @Override
    public Command execute(List<String> input) {
        throw new IllegalArgumentException(INVALID_EXECUTE_MESSAGE);
    }
}
