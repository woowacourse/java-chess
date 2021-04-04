package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandMenu;
import chess.controller.command.Show;
import chess.controller.command.Status;
import chess.controller.dto.BoardResponseDto;
import chess.controller.dto.GameResultDto;
import chess.controller.dto.ShowPathResponseDto;
import chess.controller.dto.StatusResponseDto;
import chess.domain.manager.ChessManager;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessController {

    private final ChessManager chessManager;

    public ChessController() {
        this.chessManager = new ChessManager();
    }

    public void run() {
        OutputView.printGuideStartGame();
        Command firstCommand = initFirstCommand();
        if (firstCommand.isEnd()) {
            OutputView.printEndGame();
            return;
        }
        OutputView.printBoard(BoardResponseDto.toBoard(chessManager.getBoard()));
        do {
            initCommand();
        } while (chessManager.isPlaying());
        OutputView.printGameResult(GameResultDto.toStatus(chessManager.gameStatus()));
    }

    private Command initFirstCommand() {
        try {
            List<String> userInput = InputView.getUserCommand();
            Command command = CommandMenu.findFirstCommandByInput(chessManager, userInput);
            command.execute();
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return initFirstCommand();
        }
    }

    private void initCommand() {
        try {
            final Command command =
                    CommandMenu.findRunningCommandByInput(chessManager, InputView.getUserCommand());
            command.execute();
            printRunningGame(command);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            initCommand();
        }
    }

    private void printRunningGame(final Command command) {
        if (command.isMove()) {
            OutputView.printBoard(BoardResponseDto.toBoard(chessManager.getBoard()));
        }

        if (command.isShow()) {
            Show show = (Show) command;
            OutputView.printMovablePath(BoardResponseDto.toBoard(chessManager.getBoard()),
                    ShowPathResponseDto.toPath(show.path()));
        }

        if (command.isStatus()) {
            Status status = (Status) command;
            OutputView.printStatus(StatusResponseDto.toStatus(status.gameStatus()));
        }

        if (command.isRestart()) {
            OutputView.printRestartGame(BoardResponseDto.toBoard(chessManager.getBoard()));
        }
    }
}
