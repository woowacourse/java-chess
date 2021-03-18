package chess.controller;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.Commands;
import chess.domain.command.StatusCommand;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.BoardDto;

public class ChessController {

    private final Board board;
    private final ChessGame game;
    private final Commands commands;

    public ChessController(final Board board, final ChessGame game, final Commands commands) {
        this.board = board;
        this.game = game;
        this.commands = commands;
    }

    public void run() {
        while (!game.isFinished()) {
            turn();
        }
    }

    private void turn() {
        try {
            String input = InputView.inputCommandFromUser();

            Command command = commands.getIfPresent(input);
            command.execute(input);

            printScoreIfStatusCommand(command);
            printByCommand(command);
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private void printByCommand(final Command command) {
        if (game.isFinished()) {
            return;
        }

        OutputView.drawBoard(new BoardDto(board));
    }

    private void printScoreIfStatusCommand(Command command) {
        if (!command.isStatus()) {
            return;
        }

        StatusCommand statusCommand = (StatusCommand) command;
        OutputView.printScore(statusCommand.getWhiteScore(), statusCommand.getBlackScore());
    }

}
