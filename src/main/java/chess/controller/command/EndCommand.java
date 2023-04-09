package chess.controller.command;

import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public final class EndCommand extends AbstractCommand {

    static final String COMMAND = "end";
    private static final int TOTAL_COMMAND_OPTIONS_LENGTH = 1;

    private EndCommand() {
    }

    public static EndCommand of(final List<String> commandWithOptions) {
        validateLength(commandWithOptions, TOTAL_COMMAND_OPTIONS_LENGTH);
        return new EndCommand();
    }


    @Override
    public ChessGame execute(final InputView inputView, final OutputView outputView, final ChessGameService chessGameService, final ChessGame chessGame) {
        outputView.printGameEndMessage();
        return chessGame;
    }

    @Override
    public boolean isGameEnd() {
        return true;
    }
}
