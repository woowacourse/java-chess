package chess.controller;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Objects;

public class ChessController {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int DEST_POSITION_INDEX = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printGameGuide();
        ChessGame chessGame = createChessGame();
        outputView.printChessBoard(new ChessBoardDto(chessGame.getChessBoard()));
        while (chessGame.isRunning()) {
            executeTurn(chessGame);
        }
    }

    private ChessGame createChessGame() {
        try {
            String command = inputView.readStart();
            validateStartCommand(command);
            return ChessGame.startNewGame();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return createChessGame();
        }
    }

    private static void validateStartCommand(final String command) {
        if (Objects.isNull(command) || Command.from(command) != Command.START) {
            throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
        }
    }

    private void executeTurn(final ChessGame chessGame) {
        try {
            List<String> commands = inputView.readCommands();
            Command command = Command.from(commands.get(COMMAND_INDEX));
            validateCommandSize(command, commands.size());
            executeCommand(chessGame, commands, command);
            outputView.printChessBoard(new ChessBoardDto(chessGame.getChessBoard()));
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            executeTurn(chessGame);
        }
    }

    private void executeCommand(ChessGame chessGame, List<String> commands, Command command) {
        if (command == Command.END) {
            chessGame.finishGame();
        }
        if (command == Command.MOVE) {
            String source = commands.get(SOURCE_POSITION_INDEX);
            String destination = commands.get(DEST_POSITION_INDEX);
            chessGame.executeMove(source, destination);
            chessGame.checkGameNotFinished();
        }
    }

    private void validateCommandSize(final Command command, final int size) {
        if (!command.isAppropriateSize(size)) {
            throw new IllegalArgumentException("입력된 명령어의 파라미터 개수가 올바르지 않습니다.");
        }
    }
}
