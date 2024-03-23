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
            tryMoveUntilNoError(chess);
        }
    }

    private Chess initChess() {
        Chess chess = new Chess();
        outputView.printBoard(chess.getBoard());
        return chess;
    }

    private boolean wantMove(Chess chess) {
        outputView.printTurn(chess.getTurn());
        Command command = inputView.readCommand();
        if (command.isStart()) {
            playGame();
            return false;
        }
        return !command.isEnd();
    }

    private void tryMoveUntilNoError(Chess chess) {
        try {
            tryMove(chess);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
        } finally {
            inputView.clean();
        }
    }

    private void tryMove(Chess chess) {
        Position sourcePosition = inputView.readPosition();
        Position targetPosition = inputView.readPosition();
        chess.play(sourcePosition, targetPosition);
        outputView.printBoard(chess.getBoard());
    }
}
