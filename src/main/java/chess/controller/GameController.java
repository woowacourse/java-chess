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
        ChessBoard chessBoard = new ChessBoard(new NormalPiecesGenerator());
        ChessGame chessGame = new ChessGame(chessBoard);

        OutputView.printStartMessage();
        play(chessGame);
    }

    private void play(ChessGame chessGame) {
        while (!chessGame.isFinished()) {
            requestCommand(chessGame);
            printChessBoard(chessGame);
            printWinner(chessGame);
        }
    }

    private void requestCommand(ChessGame chessGame) {
        try {
            GameCommand gameCommand = new GameCommand(InputView.inputCommand());
            chessGame.playGameByCommand(gameCommand);
            checkStatus(chessGame, gameCommand);
        } catch (RuntimeException exception) {
            OutputView.printReplay(exception.getMessage());
            requestCommand(chessGame);
        }
    }

    private void checkStatus(ChessGame chessGame, GameCommand gameCommand) {
        if (gameCommand.isSameCommandType(CommandType.STATUS)) {
            printStatus(chessGame);
        }
    }

    private void printStatus(ChessGame chessGame) {
        double whiteScore = chessGame.calculateScore(Color.WHITE);
        double blackScore = chessGame.calculateScore(Color.BLACK);
        OutputView.printStatus(Color.WHITE, whiteScore);
        OutputView.printStatus(Color.BLACK, blackScore);
    }

    private void printChessBoard(ChessGame chessGame) {
        if (!chessGame.isFinished()) {
            OutputView.printChessBoard(chessGame.getChessBoard().getPieces());
        }
    }

    private void printWinner(ChessGame chessGame) {
        if (chessGame.isEndGame()) {
            State state = chessGame.checkFinished();
            OutputView.printWinner(state.getWinner());
        }
    }
}
