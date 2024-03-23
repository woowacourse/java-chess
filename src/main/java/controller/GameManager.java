package controller;

import domain.Chess;
import domain.command.Command;
import domain.position.Position;
import view.InputView;
import view.OutputView;

public class GameManager {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        Command initCommand = requestInitCommand();
        if (initCommand.isNotStart()) {
            return;
        }
        Chess chess = initChess();
        manage(chess);
    }

    private void manage(Chess chess) { // TODO: indent 1로 줄이기
        try {
            outputView.printTurn(chess.getTurn());
            Command command = requestCommand();
            if (command.isEnd()) {
                return;
            }
            if (command.isStart()) {
                chess = initChess();
                manage(chess);
                return;
            }
            Position sourcePosition = inputView.readPosition();
            Position targetPosition = inputView.readPosition();
            chess.play(sourcePosition, targetPosition);
            outputView.printBoard(chess.getBoard());
            manage(chess);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            manage(chess);
        }
    }

    private Chess initChess() {
        Chess chess = new Chess();
        outputView.printBoard(chess.getBoard());
        return chess;
    }

    private Command requestInitCommand() {
        try {
            return inputView.readInitCommand();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return requestInitCommand();
        }
    }

    private Command requestCommand() {
        try {
            return inputView.readCommand();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return requestCommand();
        }
    }
}
