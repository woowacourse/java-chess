package chess;

import chess.controller.ChessController;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.dto.MovePositionDto;

public class ChessApplication {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final ChessController controller = new ChessController();

    public static void main(String[] args) {
        outputView.printStartMessage();
        Command command = inputView.readGameCommand();
        if (command.isStart()) {
            outputView.printBoard(controller.getBoard());
            retryWhenError(() -> startTurn(Color.WHITE));
        }
    }

    private static void startTurn(Color color) {
        Command command = inputView.readGameCommand();
        if (command.isEnd()) {
            return;
        }
        if (command.isMove()) {
            Position source = inputView.resolvePosition(command.sourcePosition());
            Position target = inputView.resolvePosition(command.targetPosition());
            outputView.printBoard(controller.move(new MovePositionDto(source, target, color)));
        }
        startTurn(Color.nextColorOf(color));
    }

    private static void retryWhenError(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException exception) {
            outputView.printMessage(exception.getMessage());
            retryWhenError(runnable);
        }
    }
}
