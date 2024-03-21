package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardFactory;
import chess.domain.Position;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

// TODO: 이름 고민 - er??
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
        GameCommand gameCommand = inputView.readGameCommand();

        if (GameCommand.START.equals(gameCommand)) {
            outputView.printChessBoard(chessBoard);
            repeat(chessBoard, this::startGame);
        }
    }

    private void startGame(final ChessBoard chessBoard) {
        GameCommand gameCommand = inputView.readGameCommand();

        do {
            if (GameCommand.MOVE.equals(gameCommand)) {
                Position source = readPosition();
                Position target = readPosition();
                chessBoard.move(source, target);
                outputView.printChessBoard(chessBoard);
            }
        } while (inputView.readGameCommand() != GameCommand.END);
    }

    private Position readPosition() {
        String position = inputView.readPosition();

        char file = position.substring(0, 1).charAt(0);
        int rank = Integer.parseInt(position.substring(1, 2));

        return Position.of(file, rank);
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
