package chess.controller;

import chess.domain.ChessGame;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.SquareDto;

public class ChessController {

    private final ChessGame chessGame = new ChessGame();

    public void run() {
        OutputView.printStartMessage();
        OutputView.printCommandMessage();
        Command initialCommand = readInitialCommand();
        if (initialCommand == Command.END) {
            return;
        }
        if (initialCommand == Command.START) {
            playGame();
        }
    }

    private Command readInitialCommand() {
        Command command = InputView.readCommand();
        try {
            validateInitialCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readInitialCommand();
        }
    }

    private void validateInitialCommand(final Command command) {
        if (command == Command.MOVE) {
            throw new IllegalArgumentException("게임 시작 전에는 기물을 이동할 수 없습니다.");
        }
    }

    private void playGame() {
        OutputView.printGameStatus(chessGame.getGameStatus());
        while (readPlayCommand() == Command.MOVE) {
            SquareDto current = readSquare();
            SquareDto destination = readSquare();
            move(current, destination);
        }
    }

    private Command readPlayCommand() {
        Command command = InputView.readCommand();
        try {
            validatePlayCommand(command);
            return command;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readPlayCommand();
        }
    }

    private void validatePlayCommand(final Command command) {
        if (command == Command.START) {
            throw new IllegalArgumentException("이미 게임을 시작하셨습니다.");
        }
    }

    private SquareDto readSquare() {
        try {
            return InputView.readSquare();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return null;
        }
    }

    private void move(final SquareDto currentDto, final SquareDto destinationDto) {
        if (currentDto == null || destinationDto == null) {
            return;
        }
        try {
            Square current = Square.of(File.from(currentDto.getFile()), Rank.from(currentDto.getRank()));
            Square destination = Square.of(File.from(destinationDto.getFile()), Rank.from(destinationDto.getRank()));
            chessGame.move(current, destination);
            OutputView.printGameStatus(chessGame.getGameStatus());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
