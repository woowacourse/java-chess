package chess;

import chess.domain.board.Board;
import chess.view.Command;
import chess.view.InputTokens;
import chess.view.InputView;
import chess.view.OutputView;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        repeatUntilLegalInput(() -> start(new Board()));
    }

    private void start(Board board) {
        InputTokens inputTokens = inputView.readCommand();
        Command command = Command.from(inputTokens);
        if (command.isStart()) {
            repeatUntilLegalInput(() -> proceed(board));
            return;
        }

        if (command.isEnd()) {
            return;
        }

        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private void proceed(Board board) {
        outputView.printBoard(board);
        InputTokens inputTokens = inputView.readCommand();
        Command command = Command.from(inputTokens);
        if (command.isMove()) {
            board.move(command.sourceCoordinate(inputTokens), command.targetCoordinate(inputTokens));
            proceed(board);
            return;
        }

        if (command.isEnd()) {
            return;
        }

        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private void repeatUntilLegalInput(final Runnable action) {
        try {
            action.run();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            repeatUntilLegalInput(action);
        }
    }
}
