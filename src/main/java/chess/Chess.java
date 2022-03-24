package chess;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.view.InputView;
import chess.view.OutputView;

public class Chess {

    private final Board board;
    private GameState gameState;

    public Chess(final Board board) {
        this.board = board;
        gameState = GameState.READY;
    }

    public void run() {
        OutputView.printStartMessage();
        while (gameState != GameState.END) {
            repeatTurn();
        }
    }

    private void repeatTurn() {
        try {
            operateOnce();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            repeatTurn();
        }
    }

    private void operateOnce() {
        String[] args = InputView.input()
            .split(" ", -1);
        Command command = Command.from(args[0]);
        if (command == Command.START && gameState == GameState.READY) {
            start();
            gameState = GameState.RUNNING;
            return;
        }
        if (command == Command.MOVE && gameState == GameState.RUNNING) {
            move(args);
            return;
        }
        if (command == Command.END) {
            gameState = GameState.END;
            return;
        }
        throw new IllegalArgumentException("현재 실행할 수 없는 명령입니다.");
    }

    private void start() {
        OutputView.printBoard(board.getPieces());
    }

    private void move(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("올바르지 않은 이동 명령입니다.");
        }
        Position start = Position.from(args[1]);
        Position target = Position.from(args[2]);
        board.move(start, target);
        OutputView.printBoard(board.getPieces());
    }
}
