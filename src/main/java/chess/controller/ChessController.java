package chess.controller;

import chess.domain.ChessGame;
import chess.dto.SquareMoveDto;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

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
            chessGame.start(InputView.readCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            start();
        }
    }

    // TODO: 개선 필요
    private void play() {
        OutputView.printGameStatus(chessGame.getGameStatus());
        while (InputView.readCommand() == Command.MOVE) {
            String current = InputView.readSquare();
            String destination = InputView.readSquare();
            try {
                move(current, destination);
                OutputView.printGameStatus(chessGame.getGameStatus());
            } catch (RuntimeException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void move(String current, String destination) {
        SquareMoveDto moveDto = SquareMoveDto.from(current, destination);
        chessGame.move(moveDto);
    }
}
