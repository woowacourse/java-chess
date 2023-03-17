package chess.controller;

import chess.domain.Board;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final Board board = Board.create();

    public void run() {
        OutputView.printStartMessage();
        start();
        play();
    }

    private void start() {
        try {
            Command command = InputView.readCommand();
            validateStartCommand(command);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            start();
        }
    }

    private void validateStartCommand(Command command) {
        if (command.equals(Command.START)) {
            return;
        }
        throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
    }

    private void play() {

    };
}
