package chess.controller;

import chess.controller.command.Command;
import chess.domain.ChessGame;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardCreator;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        Command command = inputView.readCommand();
        if (command.isNotStartCommand()) {
            return;
        }

        ChessGame chessGame = initializeChessGame();
        while (command.isNotEndCommand()) {
            command.execute(chessGame, outputView);
            command = inputView.readCommand();
        }
    }

    private ChessGame initializeChessGame() {
        ChessBoardCreator chessBoardCreator = new ChessBoardCreator();
        ChessBoard chessBoard = chessBoardCreator.create();
        return new ChessGame(chessBoard);
    }
}
