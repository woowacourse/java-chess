package chess.controller;

import chess.domain.ChessGame;
import chess.domain.exception.InvalidTurnException;
import chess.dto.SquareMoveDto;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

import static chess.view.Command.MOVE_DESTINATION_INDEX;
import static chess.view.Command.MOVE_SOURCE_INDEX;

public class ChessController {

    private final ChessGame chessGame = new ChessGame();

    public void run() {
        OutputView.printStartMessage();
        start();
        play();
        InputView.terminate();
    }

    private void start() {
        try {
            List<String> command = InputView.readCommand();
            chessGame.start(Command.from(command));
            OutputView.printGameStatus(chessGame.getGameStatus());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            start();
        }
    }

    private void play() {
        try {
            playUntilEnd();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            play();
        }
    }

    private void playUntilEnd() {
        List<String> command = InputView.readCommand();
        while (Command.from(command).isMoveCommand()) {
            move(command.get(MOVE_SOURCE_INDEX), command.get(MOVE_DESTINATION_INDEX));
            OutputView.printGameStatus(chessGame.getGameStatus());
            command = InputView.readCommand();
        }
        checkStart(command);
        checkEnd(command);
    }

    private void move(String current, String destination) {
        try {
            SquareMoveDto moveDto = SquareMoveDto.from(current, destination);
            chessGame.move(moveDto);
        } catch (InvalidTurnException e) {
            OutputView.printErrorMessage(e.getMessage());
            playUntilEnd();
        }
    }

    private void checkStart(final List<String> command) {
        if (Command.from(command).isStartCommand()) {
            chessGame.restart();
            OutputView.printGameStatus(chessGame.getGameStatus());
            playUntilEnd();
        }
    }

    private void checkEnd(final List<String> command) {
        if (!Command.from(command).isEndCommand()) {
            playUntilEnd();
        }
    }
}
