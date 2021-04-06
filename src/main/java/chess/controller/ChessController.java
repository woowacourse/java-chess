package chess.controller;

import chess.domain.Game;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        Game game = Game.newGame();
        while (!game.isEnd()) {
            selectMenu(game);
        }
    }

    private void selectMenu(Game game) {
        try {
            String input = InputView.receiveInitialResponse();
            Command.playCommand(game, input);
        } catch (RuntimeException runtimeException) {
            System.out.println(runtimeException.getMessage());
        }
    }

    public static void start(Game game, String command) {
        OutputView.printBoard(game);
    }

    public static void move(Game game, String command) {
        isStart(game);
        List<String> processedInput = Arrays.asList(command.split(" "));

        game.move(processedInput.get(1), processedInput.get(2));
        OutputView.printBoard(game);

        isEnd(game, command);
    }

    private static void isStart(Game game) {
        if (game.isNotStart()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private static void isEnd(Game game, String command) {
        if (game.isEnd()) {
            end(game, command);
            OutputView.printGameWinner(game);
        }
    }

    public static void end(Game game, String command) {
        game.end();
        status(game, command);
    }

    public static void status(Game game, String command) {
        OutputView.printScore(game);
    }
}
