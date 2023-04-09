package chess.controller.command;

import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

import static chess.util.Repeater.repeatUntilNoIAE;

public class LoadCommand extends AbstractCommand {

    static final String COMMAND = "load";
    private static final int TOTAL_COMMAND_OPTIONS_LENGTH = 1;

    private LoadCommand() {
    }

    public static LoadCommand of(final List<String> commandWithOptions) {
        validateLength(commandWithOptions, TOTAL_COMMAND_OPTIONS_LENGTH);
        return new LoadCommand();
    }

    @Override
    public ChessGame execute(final InputView inputView, final OutputView outputView, final ChessGameService chessGameService, final ChessGame chessGame) {
        validateGameEmpty(chessGame);

        final ChessGame loadedGame = repeatUntilNoIAE(() -> loadGame(inputView, chessGameService));
        outputView.printChessBoard(chessGameService.getChessBoard(loadedGame));
        return loadedGame;
    }

    private ChessGame loadGame(final InputView inputView, final ChessGameService chessGameService) {
        final int gameId = inputView.readGameId();
        return chessGameService.loadGame(gameId);
    }
}
