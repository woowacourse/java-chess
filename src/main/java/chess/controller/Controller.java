package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.piece.Color;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class Controller {

    private static final int COMMAND_INDEX = 0;

    public void run() {
        ChessGame chessGame = new ChessGame(new ChessBoard(), Color.WHITE);
        OutputView.gameStart();
        if (InputView.isStart()) {
            startGame(chessGame);
        }
    }

    public void startGame(ChessGame chessGame) {
        chessGame.start();
        OutputView.printChessBoard(chessGame.getChessBoard());
        playGame(chessGame);
    }

    public void playGame(ChessGame chessGame) {
        List<String> input = InputView.moveOrEnd();

        if (InputView.MOVE.equals(input.get(COMMAND_INDEX))) {
            chessGame.run(input);
            OutputView.printChessBoard(chessGame.getChessBoard());
            playGame(chessGame);
        }
        if (InputView.END.equals(input.get(COMMAND_INDEX)) || chessGame.isOver()) {
            endGame(chessGame);
        }
    }

    public void endGame(ChessGame chessGame) {
        OutputView.gameEnd();
        Result result = chessGame.gameResult();
        if (InputView.isStatus()) {
            OutputView.printResult(result);
        }
    }
}
