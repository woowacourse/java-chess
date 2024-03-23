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
        playGame();
    }

    private Command requestInitCommand() {
        try {
            return inputView.readInitCommand();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return requestInitCommand();
        }
    }

    private void playGame() {
        Chess chess = initChess();
        while (wantMove(chess)) {
            tryMove(chess);
        }
    }

    private Chess initChess() {
        Chess chess = new Chess();
        outputView.printBoard(chess.getBoard());
        return chess;
    }

    private boolean wantMove(Chess chess) {
        outputView.printTurn(chess.getTurn());
        Command command = requestCommand();
        if (command.isStart()) {
            playGame();
            return false;
        }
        return !command.isEnd();
    }

    private Command requestCommand() {
        try {
            return inputView.readCommand();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return requestCommand();
        }
    }

    private void tryMove(Chess chess) {
        try {
            Position sourcePosition = inputView.readPosition(); // TODO: 예외 처리
            Position targetPosition = inputView.readPosition();
            chess.play(sourcePosition, targetPosition);
            outputView.printBoard(chess.getBoard());
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        }
    }
}
