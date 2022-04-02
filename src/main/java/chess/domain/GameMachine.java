package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.RegularRuleMaker;
import chess.domain.command.Commands;
import chess.view.InputView;
import chess.view.OutputView;

public final class GameMachine {

    public void run() {
        InputView.announceStart();
        Board board = null;
        Commands commands;
        do {
            commands = InputView.requestCommands();
            board = play(board, commands);
        } while (!commands.isEnd() && !gameEnd(board));

        if (board != null) {
            OutputView.printFinalResult(board);
        }
    }

    private boolean gameEnd(Board board) {
        return board != null && board.isEnd();
    }

    private Board play(Board board, Commands commands) {
        if (commands.isStart()) {
            board = new Board(new RegularRuleMaker());
            OutputView.printBoard(board);
        }
        if (commands.isMove()) {
            movePiece(board, commands);
        }
        if (commands.isStatus()) {
            showStatus(board);
        }
        return board;
    }

    private void showStatus(Board board) {
        if (board == null) {
            OutputView.announceNotStarted();
            return;
        }
        OutputView.printScoreAndResult(board);
    }

    private void movePiece(Board board, Commands commands) {
        if (board == null) {
            OutputView.announceNotStarted();
            return;
        }
        if (commands.isRightMoveCommand()) {
            OutputView.announceWrongMoveCommand();
            return;
        }
        movePieceOnBoard(board, commands);
        OutputView.printBoard(board);
    }

    private void movePieceOnBoard(Board board, Commands command) {
        try {
            board.move(command.getSource(), command.getTarget());
        } catch (IllegalArgumentException e) {
            OutputView.announceBadMovement(e.getMessage());
        }
    }
}
