package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.feature.Color;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.gamestate.Ready;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Collections;
import java.util.List;

public class WebUIChessController {
    public ChessGame chessGame() {
        ChessBoard chessBoard = new ChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard, Color.WHITE, new Ready());
        chessGame.start(Collections.singletonList("start"));
        return chessGame;
    }

    public void playGame(ChessGame chessGame) {
        while (chessGame.isOngoing()) {
            play(chessGame);
        }
    }

    public void play(ChessGame chessGame) {
        try {
            List<String> input = InputView.takeInput();
            chessGame.play(input);
            if (chessGame.isOngoing()) {
                OutputView.printChessBoard(chessGame.getChessBoard());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            play(chessGame);
        }
    }

    public void gameResult(ChessGame chessGame) {
        try {
            Result result = chessGame.calculateResult();
            OutputView.printResult(result);
            chessGame();
        } catch (UnsupportedOperationException ignored) {
        }
    }
}
