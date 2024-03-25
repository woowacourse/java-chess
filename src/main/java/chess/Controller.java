package chess;

import chess.domain.board.Board;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        List<String> commandInput = inputView.readCommand();
        Command command = Command.from(commandInput.get(0));
        if (command.isStart()) {
            playChess(new Board());
            return;
        }

        if (command.isEnd()) {
            return;
        }

        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private void playChess(Board board) {
        outputView.printBoard(board);
        List<String> commandInput = inputView.readCommand();
        Command command = Command.from(commandInput.get(0));
        if (command.isMove()) {
            board.move(command.sourceCoordinate(commandInput.get(1)), command.targetCoordinate(commandInput.get(2)));
            playChess(board);
            return;
        }

        if (command.isEnd()) {
            return;
        }

        throw new IllegalArgumentException("잘못된 입력입니다.");
    }
}
