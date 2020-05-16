package chess.controller;

import chess.domain.board.Board;
import chess.domain.piece.state.Result;
import chess.domain.position.MovingFlow;
import chess.ui.Command;
import chess.ui.Console;
import chess.view.OutputView;

public class ConsoleChessController {
    private final Console console;

    public ConsoleChessController(Console console) {
        this.console = console;
    }

    public Board start() {
        Command command = console.inputStart();
        if (command.isNotStart() && command.isNotEnd()) {
            throw new IllegalArgumentException("입력이 잘못되었습니다.");
        }
        Board board = Board.initialize();
        OutputView.printBoard(board.serialize());
        return board;
    }

    public Board play(Board board) {
        while (board.isNotFinished()) {
            MovingFlow movingFlow = console.inputMovingFlow();
            board = board.movePiece(movingFlow);
            OutputView.printBoard(board.serialize());
        }
        return board;
    }

    public void showResult(Board board) {
        Command command = console.inputStatus();
        if (command.isNotStatus()) {
            throw new IllegalArgumentException("입력이 잘못되었습니다.");
        }
        Result result = board.concludeResult();
        OutputView.printResult(result.getWinner(), result.getWhiteScore(), result.getBlackScore());
        OutputView.printEnd();
    }
}