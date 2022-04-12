package chess.console.controller;

import chess.console.view.InputView;
import chess.console.view.OutputView;
import chess.domain.board.Position;
import chess.domain.chessgame.ChessGame;
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
        outputView.printStartMessage();
        while (!chessGame.isEndInGameOff()) {
            playGame(chessGame);
            checkKingState(chessGame);
        }
    }

    private void playGame(final ChessGame chessGame) {
        final String command = inputView.inputCommand();
        final GameCommand gameCommand = GameCommand.from(command);
        System.out.println(">>>>>" + command);
        gameCommand.execute(command, chessGame, printBoardInfoToState(chessGame));
    }

    private Runnable printBoardInfoToState(final ChessGame chessGame) {
        return () -> {
            if (chessGame.isRunning()) {
                outputView.printBoard(chessGame.getBoard());
            }
            if (chessGame.isStatusInRunning()) {
                outputView.printStatus(chessGame.calculateStatus());
            }
            if (chessGame.isEndInRunning()) {
                outputView.printFinalStatus(chessGame.calculateStatus());
            }
            if (chessGame.isEndInGameOff()) {
                outputView.printEndMessage();
            }
        };
    }

    private void checkKingState(final ChessGame chessGame) {
        if (!chessGame.isRunning()) {
            return;
        }
        if (chessGame.isKingChecked()) {
            outputView.printKingCheckedMessage();
            return;
        }

        final List<Position> kingCheckmatedPositions = chessGame.getKingCheckmatedPositions();
        if (chessGame.isAnyMovablePositionKingCheckmated(kingCheckmatedPositions)) {
            checkAllKingCheckMated(chessGame, kingCheckmatedPositions);
        }
    }

    public void checkAllKingCheckMated(final ChessGame chessGame, final List<Position> positions) {
        if (chessGame.isAllKingCheckmated(positions)) {
            outputView.printALLKingCheckmatedMessage();
            chessGame.gameSwitchOff();
            return;
        }
        outputView.printKingCheckmatedMessage(positions);
    }
}
