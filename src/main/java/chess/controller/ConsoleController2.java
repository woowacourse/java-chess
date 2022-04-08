package chess.controller;

import chess.domain.command.Command;
import chess.domain.command.CommandType;
import chess.domain.event.Event;
import chess.domain.event.InitEvent;
import chess.domain.event.MoveEvent;
import chess.domain.game.Game;
import chess.domain.game.statistics.GameResult;
import chess.dto.board.ConsoleBoardViewDto;
import chess.view.OutputView;

public class ConsoleController2 {

    private static final int EXIT_STATUS_CODE = 0;

    private final OutputView outputView = new OutputView();

    public Game run(Game game, Command command) {
        game = updateGameOnInitOrMoveCommand(game, command);
        printCurrentBoardView(game);
        printResultOnStatusCommand(game, command);
        exitOnEndCommand(command);
        return game;
    }

    private Game updateGameOnInitOrMoveCommand(Game game, Command command) {
        if (command.hasTypeOf(CommandType.INIT)) {
            game = game.play(new InitEvent());

        }
        if (command.hasTypeOf(CommandType.MOVE)) {
            Event moveEvent = new MoveEvent(command.toMoveRoute());
            game = game.play(moveEvent);
        }
        return game;
    }

    private void printCurrentBoardView(Game game) {
        ConsoleBoardViewDto boardView = game.toConsoleView();
        outputView.printBoard(boardView);
    }

    private void printResultOnStatusCommand(Game game, Command command) {
        if (command.hasTypeOf(CommandType.STATUS)) {
            GameResult result = game.getResult();
            outputView.printStatus(result);
        }
    }

    private void exitOnEndCommand(Command command) {
        if (command.hasTypeOf(CommandType.END)) {
            System.exit(EXIT_STATUS_CODE);
        }
    }
}
