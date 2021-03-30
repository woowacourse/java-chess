package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.feature.Color;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.gamestate.Ready;
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

    public void movePiece(ChessGame chessGame, List<String> input) {
        try {
            chessGame.play(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
