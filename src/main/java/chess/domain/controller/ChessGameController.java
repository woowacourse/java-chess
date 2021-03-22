package chess.domain.controller;

import chess.domain.board.ChessBoard;
import chess.view.Commands;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    private static final String ERROR_COLLECT_FORMAT = "정확한 양식에 맞춰 입력하여 주세요.";
    public static final int NUM_MOVE_COMMAND_PARAMETER = 3;

    private ChessBoard chessBoard;

    public ChessGameController(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void run() {
        try {
            while (chessBoard.isPlaying()) {
                List<String> command = InputView.command();
                if (isCommandStart(command)) {
                    OutputView.printBoard(chessBoard.getChessBoard());
                }
                if (isCommandMove(command)) {
                    runMove(command);
                }
                if (isCommandStatus(command)) {
                    OutputView.printScore(chessBoard.result());
                }
                if (isCommandEnd(command)) {
                    break;
                }
            }
        } catch (Exception exception) {
            OutputView.printErrorMessage(exception);
            run();
        }

    }

    private boolean isCommandEnd(List<String> command) {
        return Commands.getInstance(command.get(0)) == Commands.END;
    }

    private boolean isCommandStatus(List<String> command) {
        return Commands.getInstance(command.get(0)) == Commands.STATUS;
    }

    private boolean isCommandMove(List<String> command) {
        return Commands.getInstance(command.get(0)) == Commands.MOVE;
    }

    private boolean isCommandStart(List<String> command) {
        return Commands.getInstance(command.get(0)) == Commands.START;
    }

    private void runMove(List<String> command) {
        if (command.size() != NUM_MOVE_COMMAND_PARAMETER) {
            throw new IllegalArgumentException(ERROR_COLLECT_FORMAT);
        }
        chessBoard.move(command.get(1), command.get(2));
        OutputView.printBoard(chessBoard.getChessBoard());
    }
}
