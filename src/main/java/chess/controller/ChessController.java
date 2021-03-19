package chess.controller;

import chess.domain.Command;
import chess.domain.Game;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printInitMessage();
        Game game = Game.set();
        while (!game.isEnd()) {
            String input = InputView.receiveInitialResponse();
            Command.playCommand(game, input);
        }
    }

    public static void start(Game game, String command) {
        game.init();
        OutputView.printBoard(game);
    }

    public static void move(Game game, String command) {
        if (!game.isStart()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }

        String[] processedCommand = command.split(" ");
        game.move(processedCommand[1], processedCommand[2]);

        OutputView.printBoard(game);

        if (game.isEnd()) {
            end(game, command);
            //OutputView.printGameWinner(game);
        }
    }

    public static void end(Game game, String command) {
        game.end();
        status(game, command);
    }

    public static void status(Game game, String command) {
        //OutputView.printScore(game);
    }
}
