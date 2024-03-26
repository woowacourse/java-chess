package chess.controller;

import chess.controller.command.Command;
import chess.domain.game.ChessGame;
import chess.view.CommandMapper;
import chess.view.InputView;
import chess.view.OutputView;

import static chess.util.retryHelper.retryUntilNoError;
import static chess.view.CommandMapper.START;

public class ChessGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Command command;
        ChessGame game = ChessGame.newGame();
        startChessGame(game);

        do {
            command = retryUntilNoError(this::readCommand);
            command.execute(game, outputView);
        } while (!command.isEnd());
    }

    private void startChessGame(ChessGame game) {
        outputView.printStartMessage();
        Command startCommand = retryUntilNoError(this::readStartCommand);
        startCommand.execute(game, outputView);
    }

    private Command readStartCommand() {
        Command command = readCommand();
        if (command.isStart()) {
            return command;
        }
        throw new IllegalArgumentException(START.getCode() + "를 입력해야 게임이 시작됩니다.");
    }

    private Command readCommand() {
        return CommandMapper.from(inputView.readGameCommand());
    }
}
