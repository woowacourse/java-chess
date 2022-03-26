package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame;
        OutputView.printGameInitMessage();
        String option = InputView.inputOption();
        if (option.equals("start")) {
            chessGame = new ChessGame();
            OutputView.printInitialChessBoard(chessGame.getBoard());
            turn(chessGame);
        }
    }

    private void turn(ChessGame chessGame) {
        while (!chessGame.isFinish()) {
            String inputOption = InputView.inputOption();
            boolean isMoveOption = inputOption.contains("move");
            boolean isEndOption = inputOption.equals("end");
            boolean isStatusOption = inputOption.equals("status");

            if(isEndOption) {
                double whiteScore = chessGame.calculateScore(Color.WHITE);
                double blackScore = chessGame.calculateScore(Color.BLACK);
                OutputView.printScore(whiteScore, blackScore);
                OutputView.printWinner(chessGame.judgeWinner());
            }

            if (isMoveOption) {
                chessGame.movePiece(inputOption);
                OutputView.printInitialChessBoard(chessGame.getBoard());
            }

            if (isStatusOption) {
                double whiteScore = chessGame.calculateScore(Color.WHITE);
                double blackScore = chessGame.calculateScore(Color.BLACK);
                OutputView.printScore(whiteScore, blackScore);
            }
        }
        OutputView.printWinner(chessGame.judgeWinner());
    }
}

