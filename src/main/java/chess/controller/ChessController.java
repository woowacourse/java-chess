package chess.controller;

import chess.domain.board.Board;
import chess.domain.command.Commands;
import chess.domain.game.ChessGame;
import chess.domain.game.State;
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
            try {
                commands.executeIf(InputView.inputCommandFromUser());
            } catch (RuntimeException e) {
                OutputView.printExceptionMessage(e.getMessage());
                continue;
            }

            printByStatus();
        }
    }

    private void printByStatus() {
        if (game.isFinished()) {
            return;
        }
        OutputView.drawBoard(new BoardDto(board));
    }
}
