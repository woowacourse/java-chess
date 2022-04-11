package chess.controller;

import chess.domain.command.CommandType;
import chess.domain.command.GameCommand;
import chess.domain.game.ChessGame;
import chess.domain.piece.generator.NormalPiecesGenerator;
import chess.view.InputView;
import chess.view.OutputView;

public class GameController {

    public void run() {
        final ChessGame chessGame = new ChessGame(new NormalPiecesGenerator());
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
            final GameCommand gameCommand = GameCommand.of(InputView.inputCommand());
            chessGame.playGameByCommand(gameCommand);
            checkStatus(chessGame, gameCommand);
        } catch (RuntimeException exception) {
            OutputView.printReplay(exception.getMessage());
            requestCommand(chessGame);
        }
    }

    private void checkStatus(final ChessGame chessGame, final GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.STATUS)) {
            printStatus(chessGame);
        }
    }

    private void printStatus(final ChessGame chessGame) {
        OutputView.printStatus(chessGame.calculateScore());
    }

    private void printChessBoard(final ChessGame chessGame) {
        if (!chessGame.isFinished()) {
            OutputView.printChessBoard(chessGame.getChessBoard().getPieces());
        }
    }

    private void printWinner(final ChessGame chessGame) {
        if (chessGame.isEndGameByPiece()) {
            OutputView.printWinner(chessGame.getWinner());
        }
    }
}
