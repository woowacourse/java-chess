package chess.controller;

import chess.controller.dto.ChessBoardPositions;
import chess.domain.ChessGame;
import chess.domain.PiecesPosition;
import chess.view.input.command.ChessCommand;
import chess.view.input.command.ChessCommandDto;
import chess.view.input.InputView;
import chess.view.output.OutputView;
import java.util.function.Supplier;

public final class ChessController {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartPrefix();
        retryOnInvalidUserInput(inputView::readStartGame);

        ChessGame chessGame = startChessGame();
        play(chessGame);
    }

    private ChessGame startChessGame() {
        PiecesPosition piecesPosition = new PiecesPosition();
        printBoard(piecesPosition);

        return new ChessGame(piecesPosition);
    }

    private void play(ChessGame chessGame) {
        ChessCommand command;
        do {
            command = retryOnInvalidUserInput(() -> playTurn(chessGame));
        } while (command == ChessCommand.MOVE);
    }

    private ChessCommand playTurn(ChessGame chessGame) {
        ChessCommandDto chessCommandDto = inputView.readRunningCommand();
        ChessCommand chessCommand = chessCommandDto.getChessCommand();
        if (chessCommand == ChessCommand.END) {
            return chessCommand;
        }

        chessGame.move(chessCommandDto.getFromPosition(), chessCommandDto.getToPosition());
        printBoard(chessGame.getPiecesPosition());
        return chessCommand;
    }

    private void printBoard(PiecesPosition piecesPosition) {
        ChessBoardPositions chessBoardPositions = ChessBoardPositions.getInstance();
        outputView.printChessState(chessBoardPositions.getPositions(), piecesPosition.getPiecesPosition());
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }
}
