package chess.controller.command;

import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class StatusCommand extends AbstractCommand {

    static final String COMMAND = "status";
    private static final int TOTAL_COMMAND_OPTIONS_LENGTH = 1;

    private StatusCommand() {
    }

    public static StatusCommand of(final List<String> commandWithOptions) {
        validateLength(commandWithOptions, TOTAL_COMMAND_OPTIONS_LENGTH);
        return new StatusCommand();
    }

    @Override
    public ChessGame execute(final InputView inputView, final OutputView outputView, final ChessGameService chessGameService, final ChessGame chessGame) {
        validatePlaying(chessGame);

        if (chessGameService.isGameOver(chessGame)) {
            outputView.printWinner(chessGameService.getWinner(chessGame));
        } else {
            outputView.printScore(chessGameService.calculateScores(chessGame));
        }
        return chessGame;
    }
}
