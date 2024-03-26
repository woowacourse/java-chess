package controller;

import domain.Chess;
import domain.board.Turn;
import domain.position.Position;
import view.InputView;
import view.OutputView;

public class GameManager {

    private final InputView inputView;
    private final OutputView outputView;

    public GameManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printStartNotice();
        Command initCommand = requestInitCommand();
        if (initCommand.isStart()) {
            playGame();
        }
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
        while (wantMove(chess.getTurn())) {
            tryMoveUntilNoError(chess);
        }
    }

    private Chess initChess() {
        Chess chess = new Chess();
        outputView.printBoard(chess.getBoard());
        return chess;
    }

    private boolean wantMove(Turn turn) {
        outputView.printTurn(turn);
        Command command = requestCommand();
        if (command.isEnd()) {
            return false;
        }
        if (command.isStart()) {
            playGame();
            return false;
        }
        return true;
    }

    private Command requestCommand() {
        try {
            return inputView.readCommand();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            inputView.clean();
            return requestCommand();
        }
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
        chess.tryMove(sourcePosition, targetPosition);
        outputView.printBoard(chess.getBoard());
    }
}
