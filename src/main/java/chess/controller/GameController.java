package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.CommandType;
import chess.domain.GameCommand;
import chess.domain.State.State;
import chess.domain.piece.Color;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.view.InputView;
import chess.view.OutputView;

public class GameController {

    public void run() {
        final ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());
        final ChessGame chessGame = new ChessGame(chessBoard);

        play(chessGame);
    }

    private void play(final ChessGame chessGame) {
        OutputView.printStartMessage();
        while (!chessGame.isFinished()) {
            requestCommand(chessGame);
            printChessBoard(chessGame);
            printWinner(chessGame);
        }
    }

    private void requestCommand(final ChessGame chessGame) {
        try {
            final GameCommand gameCommand = new GameCommand(InputView.inputCommand());
            chessGame.playGameByCommand(gameCommand);
            checkStatus(chessGame, gameCommand);
        } catch (RuntimeException exception) {
            OutputView.printReplay(exception);
            requestCommand(chessGame);
        }
    }

    private void checkStatus(final ChessGame chessGame, final GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.STATUS)) {
            printStatus(chessGame);
        }
    }

    private void printStatus(final ChessGame chessGame) {
        final double whiteScore = chessGame.calculateScore(Color.WHITE);
        final double blackScore = chessGame.calculateScore(Color.BLACK);

        OutputView.printStatus(Color.WHITE, whiteScore);
        OutputView.printStatus(Color.BLACK, blackScore);
    }

    private void printChessBoard(final ChessGame chessGame) {
        if (!chessGame.isFinished()) {
            OutputView.printChessBoard(chessGame);
        }
    }

    private void printWinner(final ChessGame chessGame) {
        if (chessGame.isEndGameByPiece()) {
            final State state = chessGame.getWinnerState();
            OutputView.printWinner(state.getWinner());
        }
    }
}
