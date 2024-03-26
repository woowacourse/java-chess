package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardFactory;
import chess.domain.Position;
import chess.view.CommandExpression;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.function.Consumer;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final ChessBoard chessBoard = ChessBoardFactory.makeChessBoard();
        outputView.printCommandInformation();
        CommandExpression commandExpression = inputView.readCommand();

        if (commandExpression.isStart()) {
            outputView.printChessBoard(chessBoard);
            repeat(chessBoard, this::startGame);
        }
    }

    private void startGame(final ChessBoard chessBoard) {
        CommandExpression commandExpression = inputView.readCommand();

        while (commandExpression.isMove()) {
            Position source = commandExpression.getSourcePosition();
            Position target = commandExpression.getTargetPosition();
            chessBoard.move(source, target);
            outputView.printChessBoard(chessBoard);
            commandExpression = inputView.readCommand();
        }

        validateStartDuplicate(commandExpression);
    }

    private void validateStartDuplicate(CommandExpression commandExpression) {
        if (commandExpression.isStart()) {
            throw new IllegalArgumentException("게임 도중에는 start 명령어를 입력할 수 없습니다.");
        }
    }

    private void repeat(final ChessBoard chessBoard, final Consumer<ChessBoard> consumer) {
        try {
            consumer.accept(chessBoard);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            repeat(chessBoard, consumer);
        }
    }
}
