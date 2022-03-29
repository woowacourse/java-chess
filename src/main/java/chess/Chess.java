package chess;

import chess.command.CommandChain;
import chess.command.ParsedCommand;
import chess.command.Start;
import chess.domain.Status;
import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class Chess {

    private final Board board;
    private final CommandChain commandChain;

    public Chess(final Board board) {
        this.board = board;
        commandChain = new Start();
    }

    public void run() {
        OutputView.printStartMessage();
        while (!board.isEnd()) {
            repeatTurn();
        }
        OutputView.printStatus(new Status(board));
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
        commandChain.doCommandAction(new ParsedCommand(InputView.input()), board);
    }

}
