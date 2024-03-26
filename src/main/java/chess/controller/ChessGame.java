package chess.controller;

import chess.model.board.ChessBoard;
import chess.model.board.ChessBoardInitializer;
import chess.model.piece.Side;
import chess.model.position.ChessPosition;
import chess.model.game.Turn;
import chess.view.input.GameArguments;
import chess.view.input.GameCommand;
import chess.view.input.InputView;
import chess.view.input.MoveArguments;
import chess.view.output.OutputView;

import java.util.Objects;
import java.util.function.Supplier;

public class ChessGame {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        GameCommand gameCommand = retryOnException(this::getFirstGameCommand);
        if (gameCommand.isEnd()) {
            return;
        }
        ChessBoardInitializer chessBoardInitializer = new ChessBoardInitializer();
        ChessBoard chessBoard = new ChessBoard(chessBoardInitializer.create());
        Turn firstTurn = Turn.from(Side.WHITE);
        outputView.printChessBoard(chessBoard);
        retryOnException(() -> play(chessBoard, firstTurn));
    }

    private GameCommand getFirstGameCommand() {
        return GameCommand.createInPreparation(inputView.readGameCommand());
    }

    private void play(ChessBoard chessBoard, Turn firstTurn) {
        Turn turn = firstTurn;
        while (true) {
            GameArguments gameArguments = inputView.readMoveArguments();
            GameCommand gameCommand = gameArguments.gameCommand();
            if (gameCommand.isEnd()) {
                break;
            }
            MoveArguments moveArguments = gameArguments.moveArguments();
            move(chessBoard, moveArguments, turn);
            turn = turn.getNextTurn();
        }
    }

    private void move(ChessBoard chessBoard, MoveArguments moveArguments, Turn turn) {
        ChessPosition source = moveArguments.createSourcePosition();
        ChessPosition target = moveArguments.createTargetPosition();
        chessBoard.move(source, target, turn);
        outputView.printChessBoard(chessBoard);
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

    private <T> T tryOperation(Supplier<T> operation) {
        try {
            return operation.get();
        } catch (IllegalArgumentException e) {
            outputView.printException(e.getMessage());
            return null;
        }
    }

    private void retryOnException(Runnable retryOperation) {
        boolean retry = true;
        while (retry) {
            retry = tryOperation(retryOperation);
        }
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
}
