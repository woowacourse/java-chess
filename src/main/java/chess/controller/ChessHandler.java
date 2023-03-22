package chess.controller;

import chess.controller.status.Controller;
import chess.controller.status.StartController;
import chess.domain.chess.ChessGame;
import chess.service.ChessBoardService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public final class ChessHandler {

    private final ChessBoardService chessBoardService;

    public ChessHandler(final ChessBoardService chessBoardService) {
        this.chessBoardService = chessBoardService;
    }

    public void run() {
        OutputView.printStartMessage();
        final ChessGame chessGame = new ChessGame();
        start(chessGame);
    }

    private void start(final ChessGame chessGame) {
        Controller controller = new StartController(chessGame);
        while (controller.isRun()) {
            controller = play(chessGame, controller);
        }
    }

    private Controller play(final ChessGame chessGame, Controller controller) {
        try {
            List<String> commands = InputView.getCommand();
            final Command command = Command.findCommand(commands);
            controller = controller.checkCommand(command,
                    () -> OutputView.printBoard(
                            chessBoardService.createChessBoardDto(chessGame.getChessBoard())
                    ));
            return controller;
        } catch (IllegalArgumentException e) {
            OutputView.print(e.getMessage());
            return play(chessGame, controller);
        }
    }
}

