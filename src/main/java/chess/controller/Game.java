package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardSerializer;
import chess.domain.board.Result;
import chess.domain.board.RunningBoard;
import chess.domain.ui.UserInterface;
import chess.ui.Command;
import chess.view.OutputView;

public class Game {
    private final UserInterface userInterface;

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
    }


    public Board start() {
        Command command = userInterface.inputStart();
        if (command.isNotStart() && command.isNotEnd()) {
            throw new IllegalArgumentException("입력이 잘못되었습니다.");
        }
        Board board = RunningBoard.initiaize(userInterface);
        OutputView.printBoard(BoardSerializer.serialize(board));
        return board;
    }

    public Board play(Board board) {
        while (board.isNotFinished()) {
            board = board.movePiece();
            OutputView.printBoard(BoardSerializer.serialize(board));
        }
        return board;
    }

    public void showResult(Board board) {
        Command command = userInterface.inputStatus();
        if (command.isNotStatus()) {
            throw new IllegalArgumentException("입력이 잘못되었습니다.");
        }
        Result result = board.concludeResult();
        OutputView.printResult(result.getWinner(), result.getLoser(), result.getWinnerScore(), result.getLoserScore());
        OutputView.printEnd();
    }
}
