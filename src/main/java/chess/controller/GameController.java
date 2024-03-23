package chess.controller;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;
import java.util.Optional;

public class GameController {
    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    private final Map<Command, Runnable> commandFunctions;
    private final ChessGame chessGame;

    public GameController() {
        chessGame = new ChessGame();
        commandFunctions = Map.of(
                Command.START, this::initBoard,
                Command.MOVE, this::move,
                Command.END, this::end
        );
    }


    public void run() {
        OUTPUT_VIEW.printGameStart();
        runOrRetry();
    }

    private void runOrRetry(){
        try {
            play();
        } catch (IllegalArgumentException | IllegalStateException exception) {
            OUTPUT_VIEW.printException(exception);
            runOrRetry();
        }
    }

    private void play() {
        while (chessGame.isPlayable()) {
            Command command = INPUT_VIEW.readCommand();
            Optional.ofNullable(commandFunctions.get(command))
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 커멘드 입력입니다."))
                    .run();
        }
    }

    private void initBoard() {
        chessGame.initBoard();
        OUTPUT_VIEW.printBoard(chessGame.getBoard());
    }

    private void move() {
        String sourceInput = INPUT_VIEW.readLocation();
        String targetInput = INPUT_VIEW.readLocation();
        Location source = Location.of(sourceInput);
        Location target = Location.of(targetInput);
        chessGame.tryMove(source, target);
        OUTPUT_VIEW.printBoard(chessGame.getBoard());
    }

    private void end() {
        chessGame.endGame();
    }
}
