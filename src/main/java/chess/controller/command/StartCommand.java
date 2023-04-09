package chess.controller.command;

import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

import static chess.util.Repeater.repeatUntilNoIAE;

public class StartCommand extends AbstractCommand {

    static final String COMMAND = "start";
    private static final int TOTAL_COMMAND_OPTIONS_LENGTH = 1;

    private StartCommand() {
    }

    public static StartCommand of(final List<String> commandWithOptions) {
        validateLength(commandWithOptions, TOTAL_COMMAND_OPTIONS_LENGTH);
        return new StartCommand();
    }

    @Override
    public ChessGame execute(final InputView inputView, final OutputView outputView, final ChessGameService chessGameService, final ChessGame chessGame) {
        validateGameNotStarted(chessGame);

        outputView.printNewGameStartingMessage();
        final ChessGame newGame = repeatUntilNoIAE(() -> startNewGame(inputView, chessGameService));
        outputView.printChessBoard(chessGameService.getChessBoard(newGame));
        return newGame;
    }

    private void validateGameNotStarted(final ChessGame chessGame) {
        if (chessGame != null) {
            System.out.println("플레이 중인 게임이 있습니다");
        }
    }

    private ChessGame startNewGame(final InputView inputView, final ChessGameService chessGameService) {
        final long gameId = inputView.readGameId();
        return chessGameService.createNewGame(gameId);
    }
}
