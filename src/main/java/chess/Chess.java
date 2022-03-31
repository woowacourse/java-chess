package chess;

import chess.command.CommandChain;
import chess.command.ParsedCommand;
import chess.command.Start;
import chess.domain.Status;
import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class Chess {

    public void run(final Board board) {
        OutputView.printStartMessage();
        while (!board.isEnd()) {
            repeatTurn(board);
        }
        OutputView.printStatus(new Status(board));
    }

    private void repeatTurn(final Board board) {
        try {
            operateOnce(board);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            repeatTurn(board);
        }
    }

    private void operateOnce(final Board board) {
        final CommandChain commandChain = new Start();
        commandChain.doCommandAction(new ParsedCommand(InputView.input()), board);
    }


}
