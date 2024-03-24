package controller;

import domain.piece.Piece;
import domain.square.Square;
import service.ChessGame;
import service.command.ChessCommand;
import view.InputView;
import view.OutputView;

import java.util.Map;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        final ChessGame chessGame = new ChessGame();

        outputView.printStartHeader();

        play(chessGame);
    }

    private void play(final ChessGame chessGame) {
        while (chessGame.isNotEnd()) {
            try {
                final ChessCommand chessCommand = inputView.readCommand();
                chessCommand.execute(chessGame, this::printChessBoard);
            } catch (final IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void printChessBoard(final ChessGame chessGame) {
        final Map<Square, Piece> pieceSquares = chessGame.getPieceSquares();
        outputView.printChessBoard(pieceSquares);
    }
}
