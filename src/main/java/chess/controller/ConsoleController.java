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

public class ConsoleController {

    private static final int EXIT_STATUS_CODE = 0;

    private final OutputView outputView = new OutputView();

    public Game run(Game game, Command command) {
        try {
            game = processCommand(game, command);
        } catch (Exception e) {
            OutputView.print(e.getMessage());
        }
        return game;
    }

    private Game processCommand(Game game, Command command) {
        game = updateGameOnInitOrMoveCommand(game, command);
        printResultOnStatusCommand(game, command);
        exitOnEndCommand(command);

        return game;
    }

    private Game updateGameOnInitOrMoveCommand(Game game, Command command) {
        if (command.hasTypeOf(CommandType.INIT)) {
            game = updateAndPrintCurrentBoardView(game, new InitEvent());
        }
        if (command.hasTypeOf(CommandType.MOVE)) {
            Event moveEvent = new MoveEvent(command.toMoveRoute());
            game = updateAndPrintCurrentBoardView(game, moveEvent);
        }
        return game;
    }

    private Game updateAndPrintCurrentBoardView(Game game, Event moveEvent) {
        game = game.play(moveEvent);
        ConsoleBoardViewDto boardView = game.toConsoleView();
        outputView.printBoard(boardView);
        return game;
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
