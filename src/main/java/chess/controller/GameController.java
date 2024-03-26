package chess.controller;

import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.MoveCommand;
import chess.view.OutputView;

public class GameController {
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    private Board board = null;

    public void run() {
        outputView.printGameStart();
        proceed();
    }

    private void proceed() {
        try {
            play();
        } catch (RuntimeException exception) {
            outputView.printException(exception);
            proceed();
        }
    }

    private void play() {
        String command = inputView.readCommand();
        while (!"end".equalsIgnoreCase(command)) {
            playTurn(command);
            command = inputView.readCommand();
        }
    }

    private void playTurn(String command) {
        if ("start".equalsIgnoreCase(command)) {
            createBoard();
            return;
        }
        move(command);
    }

    private void createBoard() {
        board = new Board();
        outputView.printBoard(board.getBoard());
    }

    private void move(String command) {
        checkBoard();
        String[] commands = command.split(" ");
        MoveCommand moveCommand = new MoveCommand(commands[1], commands[2]);
        board.tryMove(moveCommand);
        outputView.printBoard(board.getBoard());
    }

    private void checkBoard() {
        if (board == null) {
            throw new IllegalStateException("아직 게임이 시작되지 않았습니다.");
        }
    }
}
