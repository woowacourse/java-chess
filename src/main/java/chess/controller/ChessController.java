package chess.controller;

import chess.domain.*;
import chess.view.GameCommand;
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

        int fileIdx = Integer.parseInt(position.substring(0, 1));
        int rankIdx = Integer.parseInt(position.substring(1, 2));
        File file = File.values()[fileIdx];
        Rank rank = Rank.values()[rankIdx];

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
