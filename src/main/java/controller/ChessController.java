package controller;

import domain.ChessGameResult;
import domain.chessboard.ChessBoardDao;
import domain.piece.Piece;
import domain.square.Square;
import repository.ChessConnectionGenerator;
import service.ChessGame;
import service.command.ChessCommand;
import view.InputView;
import view.OutputView;

import java.sql.Connection;
import java.util.Map;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        final Connection connection = ChessConnectionGenerator.getConnection();
        final ChessBoardDao chessBoardDao = new ChessBoardDao(connection);
        final ChessGame chessGame = new ChessGame(chessBoardDao);

        outputView.printStartHeader();

        play(chessGame);
    }

    private void play(final ChessGame chessGame) {
        while (chessGame.isNotEnd()) {
            try {
                final ChessCommand chessCommand = inputView.readCommand();
                chessCommand.execute(chessGame, this::printResult);
            } catch (final IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
        printResult(chessGame, true);
    }

    private void printResult(final ChessGame chessGame, final boolean isStatus) {
        if (isStatus) {
            final ChessGameResult chessGameResult = chessGame.calculateResult();
            outputView.printStatus(chessGameResult);
            return;
        }

        final Map<Square, Piece> pieceSquares = chessGame.getPieceSquares();
        outputView.printChessBoard(pieceSquares);
    }
}
