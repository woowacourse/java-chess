package chess.controller.console;

import chess.controller.console.dto.board.BoardResponseDto;
import chess.controller.console.dto.position.MovablePathResponseDto;
import chess.controller.console.dto.result.GameResultResponseDto;
import chess.domain.command.Command;
import chess.domain.command.CommandMenu;
import chess.domain.command.Show;
import chess.domain.command.Status;
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
        OutputView.printBoard(BoardResponseDto.from(chessManager.getBoard()));
        do {
            initCommand();
        } while (chessManager.isPlaying());
        OutputView.printGameResult(GameResultResponseDto.from(chessManager.gameStatus()));
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
            OutputView.printBoard(BoardResponseDto.from(chessManager.getBoard()));
        }

        if (command.isShow()) {
            Show show = (Show) command;
            OutputView.printMovablePath(BoardResponseDto.from(chessManager.getBoard()),
                    MovablePathResponseDto.from(show.path()));
        }

        if (command.isStatus()) {
            Status status = (Status) command;
            OutputView.printStatus(GameResultResponseDto.from(status.gameStatus()));
        }

        if (command.isRestart()) {
            OutputView.printRestartGame(BoardResponseDto.from(chessManager.getBoard()));
        }
    }
}
