package chess.controller;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.view.InputView;
import chess.view.OutputView;

public class GameController {
    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    private Board board = new Board();
    private GameState state = GameState.PLAYING;

    public void run() {
        OUTPUT_VIEW.printGameStart();
        try {
            play();
        } catch (RuntimeException exception) {
            OUTPUT_VIEW.printException(exception);
            run();
        }
    }

    private void play() {
        while (state.isPlaying()) {
            Command command = INPUT_VIEW.readCommand();
            playTurn(command);
        }
    }

    private void playTurn(Command command) {
        if (command == Command.END) {
            state = GameState.END;
        }
        if (command == Command.START) {
            initBoard();
        }
        if (command == Command.MOVE) {
            move();
        }
    }

    private void initBoard() {
        board = new Board();
        OUTPUT_VIEW.printBoard(board.getBoard());
    }

    private void move() {
        Location source = Location.of(INPUT_VIEW.readLocation());
        Location target = Location.of(INPUT_VIEW.readLocation());
        board.tryMove(source, target);
        OUTPUT_VIEW.printBoard(board.getBoard());
    }
}
