package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printGameInitMessage();
        while (!chessGame.isFinish()) {
            selectMenu(chessGame);
        }
    }

    public void selectMenu(ChessGame chessGame) {
        String option = InputView.inputOption();
        Command.playCommand(chessGame, option);
    }

    public static void initBoard(ChessGame chessGame, String input) {
        chessGame.initBoard();
        OutputView.printInitialChessBoard(chessGame.getBoard());
    }

    public static void move(ChessGame chessGame, String input) {
        chessGame.movePiece(input);
        OutputView.printInitialChessBoard(chessGame.getBoard());
        if (!chessGame.isAllKingExist()) {
            OutputView.printWinner(chessGame.judgeWinner());
        }
    }

    public static void showStatus(ChessGame chessGame, String input) {
        OutputView.printScore(chessGame.calculateScore(Color.WHITE),
            chessGame.calculateScore(Color.BLACK));
    }

    public static void end(ChessGame chessGame, String input) {
        chessGame.endGame();
    }
}

