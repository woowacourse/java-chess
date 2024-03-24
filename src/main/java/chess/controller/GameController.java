package chess.controller;

import chess.domain.chessGame.ChessGame;
import chess.domain.chessGame.InitialGame;
import chess.domain.location.Location;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;
import java.util.Optional;

public class GameController {
    private static final InputView INPUT_VIEW = new InputView();
    private static final OutputView OUTPUT_VIEW = new OutputView();

    private final Map<Command, Runnable> commandFunctions;
    private ChessGame chessGame;

    public GameController() {
        chessGame = new InitialGame();
        commandFunctions = Map.of(
                Command.START, this::startGame,
                Command.MOVE, this::move,
                Command.END, this::end
        );
    }


    public void run() {
        OUTPUT_VIEW.printGameStart();
        runOrRetry();
    }

    private void runOrRetry() {
        try {
            play();
        } catch (IllegalArgumentException | IllegalStateException exception) {
            OUTPUT_VIEW.printExceptionMessage(exception.getMessage());
            runOrRetry();
        } catch (RuntimeException exception) {
            OUTPUT_VIEW.printExceptionMessage("예기치 못한 동작입니다. 다시 명령어를 입력해 주세요.");
            runOrRetry();
        }
    }

    private void play() {
        while (chessGame.isNotEnd()) {
            Command command = INPUT_VIEW.readCommand();
            Optional.ofNullable(commandFunctions.get(command))
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 커멘드 입력입니다."))
                    .run();
        }
    }

    private void startGame() {
        chessGame = chessGame.startGame(INPUT_VIEW::checkRestartGame);
        OUTPUT_VIEW.printBoard(chessGame.getBoard());
    }

    private void move() {
        String sourceInput = INPUT_VIEW.readLocation();
        String targetInput = INPUT_VIEW.readLocation();

        Location source = Location.of(sourceInput);
        Location target = Location.of(targetInput);

        chessGame.move(source, target);
        OUTPUT_VIEW.printBoard(chessGame.getBoard());
    }

    private void end() {
        chessGame = chessGame.endGame();
    }
}
