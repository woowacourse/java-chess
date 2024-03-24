package chess.controller;

import chess.model.board.ChessBoard;
import chess.model.board.ChessBoardInitializer;
import chess.model.position.ChessPosition;
import chess.view.GameArguments;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.MoveArguments;
import chess.view.OutputView;
import java.util.Objects;
import java.util.function.Supplier;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        GameCommand gameCommand = retryOnException(
                () -> GameCommand.createFirstGameCommand(inputView.readGameCommand())
        );
        if (gameCommand.isEnd()) {
            return;
        }
        ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.create());
        outputView.printChessBoard(chessBoard);
        retryOnException(() -> playChess(chessBoard));
    }

    private void playChess(ChessBoard chessBoard) {
        while (true) {
            GameArguments gameArguments = inputView.readMoveArguments();
            GameCommand gameCommand = gameArguments.gameCommand();
            if (gameCommand.isEnd()) {
                break;
            }
            MoveArguments moveArguments = gameArguments.moveArguments();
            move(chessBoard, moveArguments);
        }
    }

    private void move(ChessBoard chessBoard, MoveArguments moveArguments) {
        ChessPosition source = moveArguments.createSourcePosition();
        ChessPosition target = moveArguments.createTargetPosition();
        chessBoard.move(source, target);
        outputView.printChessBoard(chessBoard);
    }

    private void retryOnException(Runnable retryOperation) {
        boolean retry = true;
        while (retry) {
            retry = tryOperation(retryOperation);
        }
    }

    private <T> T retryOnException(Supplier<T> retryOperation) {
        boolean retry = true;
        T result = null;
        while (retry) {
            result = tryOperation(retryOperation);
            retry = Objects.isNull(result);
        }
        return result;
    }

    private boolean tryOperation(Runnable operation) {
        try {
            operation.run();
            return false;
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
            return true;
        }
    }

    private <T> T tryOperation(Supplier<T> operation) {
        try {
            return operation.get();
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
            return null;
        }
    }
}
