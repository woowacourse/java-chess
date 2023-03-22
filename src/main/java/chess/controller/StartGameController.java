package chess.controller;

import chess.application.ChessGameService;
import chess.controller.command.StartCommand;
import chess.controller.command.StartCommandType;
import chess.domain.board.ChessBoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class StartGameController {

    private final ChessGameService chessGameService;
    private final ChessBoardFactory chessBoardFactory;
    private final RunningGameController controller;

    public StartGameController(final ChessGameService chessGameService, final ChessBoardFactory chessBoardFactory, final RunningGameController controller) {
        this.chessGameService = chessGameService;
        this.chessBoardFactory = chessBoardFactory;
        this.controller = controller;
    }

    public void start() {
        OutputView.printStartMessage();
        while (true) {
            try {
                StartCommand startCommand = readCommand();
                if (startCommand.type() == StartCommandType.START) {
                    createGameAndStart();
                    return;
                }
                restartGame(startCommand);
                return;
            } catch (Exception e) {
                OutputView.error(e.getMessage());
            }
        }
    }

    private void createGameAndStart() {
        final Long id = chessGameService.create(chessBoardFactory);
        controller.startGame(id);
    }

    private void restartGame(final StartCommand startCommand) {
        controller.startGame(startCommand.restartParameter());
    }

    private StartCommand readCommand() {
        final List<String> commands = InputView.readCommand();
        return StartCommand.parse(commands);
    }
}
