package chess.console;

import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.domain.board.Position;
import chess.domain.command.GameCommand;
import java.util.List;

public class ConsoleController {
    private final OutputView outputView;
    private final InputView inputView;

    public ConsoleController(final OutputView outputView, final InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void start() {
        final ChessGame chessGame = new ChessGame();
//        OutputView.printStartMessage();
        outputView.printStartMessage();
        while (!chessGame.isEndInGameOff()) {
            playGame(chessGame);
            checkKingState(chessGame);
        }
    }

    private void playGame(final ChessGame chessGame) {
        final String command = inputView.inputCommand();
        final GameCommand gameCommand = GameCommand.from(command);
        gameCommand.execute(command, chessGame, printBoardInfoToState(chessGame));
    }

    private Runnable printBoardInfoToState(final ChessGame chessGame) {
        return () -> {
            if (chessGame.isRunning()) {
                OutputView.printBoard(chessGame.getBoard());
            }
            if (chessGame.isStatusInRunning()) {
                OutputView.printStatus(chessGame.calculateStatus());
            }
            if (chessGame.isEndInRunning()) {
                OutputView.printFinalStatus(chessGame.calculateStatus());
            }
            if (chessGame.isEndInGameOff()) {
                OutputView.printEndMessage();
            }
        };
    }

    private void checkKingState(final ChessGame chessGame) {
        if (!chessGame.isRunning()) {
            return;
        }
        if (chessGame.isKingChecked()) {
            OutputView.printKingCheckedMessage();
            return;
        }

        final List<Position> kingCheckmatedPositions = chessGame.getKingCheckmatedPositions();
        if (chessGame.isAnyMovablePositionKingCheckmated(kingCheckmatedPositions)) {
            chessGame.checkAllKingCheckMated(kingCheckmatedPositions);
        }
    }

}
