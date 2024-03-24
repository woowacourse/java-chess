package chess;

import java.util.function.Supplier;
import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.MoveCommand;
import chess.view.OutputView;


// TODO: 컨트롤러 이름, 패키지 포함해서 더욱 개선해볼 것!
class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        boolean isPlayable = handleException(inputView::readStartCommand);

        if (isPlayable) {
            Board board = new Board();
            outputView.printBoard(board);
            play(board, handleException(inputView::readMoveCommand));
        }
    }

    //TODO: 재귀가 아니고 할 수 있는 방법은?
    private void play(Board board, MoveCommand moveCommand) {
        if (moveCommand.isEnd()) {
            return;
        }

        try {
            board.move(moveCommand.source(), moveCommand.target());
            outputView.printBoard(board);
            play(board, handleException(inputView::readMoveCommand));
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            play(board, handleException(inputView::readMoveCommand));
        }
    }

    private <T> T handleException(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            return handleException(supplier);
        }
    }
}
