package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardOutput;
import chess.domain.position.Square;
import chess.util.RetryUtil;
import chess.view.GameStatus;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.UserCommand;

public class ChessGame {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void play() {
        UserCommand command = RetryUtil.retryUntilNoException(inputView::readProgressCommand);

        if (!isStart(command)) {
            return;
        }

        Board board = new Board();
        outputView.writeBoard(BoardOutput.of(board));

        playUntilEnd(board);
    }

    private boolean isStart(UserCommand command) {
        return command.gameStatus().equals(GameStatus.START);
    }

    private void playUntilEnd(Board board) {
        boolean run = true;

        while (run) {
            run = RetryUtil.retryUntilNoException(() -> loopWhileEnd(board));
        }
    }

    private boolean loopWhileEnd(Board board) {
        UserCommand command = RetryUtil.retryUntilNoException(inputView::readMoveCommand);

        if (isEnd(command.gameStatus())) {
            return false;
        }

        movePiece(board, command);
        outputView.writeBoard(BoardOutput.of(board));

        return true;
    }

    private boolean isEnd(GameStatus gameStatus) {
        return gameStatus.equals(GameStatus.END);
    }

    private void movePiece(Board board, UserCommand command) {
        Square source = Square.findByName(command.source());
        Square destination = Square.findByName(command.destination());

        board.move(source, destination);
    }
}
